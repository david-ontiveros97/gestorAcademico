package com.gestoracademico.usuarios.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.gestoracademico.usuarios.dto.UsuariosDto;
import com.gestoracademico.usuarios.entity.Rol;
import com.gestoracademico.usuarios.entity.Usuarios;
import com.gestoracademico.usuarios.mapper.UsuariosMapper;
import com.gestoracademico.usuarios.repository.RolesRepository;
import com.gestoracademico.usuarios.repository.UsuarioRepository;
import com.gestoracademico.usuarios.utils.MessageUtils;
import com.gestoracademico.usuarios.utils.Utils;
import com.gestoracademico.usuarios.utils.constants.EstadoEnum;
import com.gestoracademico.usuarios.utils.constants.MessagesConstants;
import com.gestoracademico.usuarios.utils.response.exception.ResponseUserException;
import com.gestoracademico.usuarios.utils.response.exception.UsuarioNoEncontradoExeption;

@Service
public class UsuariosServiceImpl implements UsuariosService {

	private final Logger log = LoggerFactory.getLogger(UsuariosServiceImpl.class);

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private RolesRepository rolRepository;

	@Autowired
	private UsuariosMapper usuariosMapper;

	@Autowired
	private MessageUtils messageUtils;

	@Override
	public void guardarUsuario(UsuariosDto usuariosDTO) {
		Usuarios user = findUserByEmail(usuariosDTO.getEmail());

		if (existeUsuario(user)) {
			log.error("El usuario: {}", usuariosDTO.getUsername() + " Ya esta registrado ");
			throw new ResponseUserException(HttpStatus.BAD_REQUEST,
					messageUtils.getMessage(MessagesConstants.UNREGISTERED_USER));
		}
		activarUsuario(usuariosDTO);
		crearYGuardarUsuario(usuariosDTO);
	}

	@Override
	public UsuariosDto buscarUsuario(String nombreUsuarioOrEmail) {
		Usuarios usuarioEntity = null;
		if (Utils.isEmailOrUsername(nombreUsuarioOrEmail)) {
			usuarioEntity = userRepository.findByEmail(nombreUsuarioOrEmail);
		} else {
			usuarioEntity = userRepository.findByusername(nombreUsuarioOrEmail);
		}

		return usuarioEntity != null ? usuariosMapper.usuarioEntityToUsuarioDTO(usuarioEntity) : null;
	}
	
	@Override
	public void borrarUsuarioById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UsuarioNoEncontradoExeption(messageUtils.getMessage(MessagesConstants.USER_NOT_FOUND));
		}
		userRepository.deleteByIdUsuario(id);
	}


	private void crearYGuardarUsuario(UsuariosDto usuariosDTO) {
		Usuarios usuariosEntity = usuariosMapper.usuariosDTOToUsuariosEntity(usuariosDTO,
				Utils.encryptPassword(usuariosDTO.getPassword()), buscarRolByIdRol(usuariosDTO.getNombreRol()));
		try {
			userRepository.save(usuariosEntity);
		} catch (DataIntegrityViolationException e) {
			log.error("Error al guardar usuario. Detalles : {} ", e.getMessage());
		} finally {
			Utils.limpiarCharArrays(usuariosDTO.getPassword());
		}

	}

	private List<Rol> buscarRolByIdRol(String nombreRol) {
		List<Rol> listaRol;
		Optional<Rol> rol = rolRepository.findByNombre(nombreRol);
		listaRol = Utils.optionalToList(rol);
		return listaRol;
	}

	private Usuarios findUserByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}

	private boolean existeUsuario(Usuarios user) {
		boolean existeUsuario = true;
		if (Objects.isNull(user)) {
			existeUsuario = false;
		}
		return existeUsuario;
	}

	private UsuariosDto activarUsuario(UsuariosDto usuariosDto) {
		usuariosDto.setEstatus(EstadoEnum.ACTIVO.getValor());
		usuariosDto.setFechaAlta(Utils.obtenerHoraActual());
		return usuariosDto;
	}

	
}