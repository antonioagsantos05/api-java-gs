package com.globalsolution2.fiap.model;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class UsuarioModel implements UserDetails{
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_usuario")
	    private Long idUsuario;

	    @Column(name = "nm_usuario", nullable = false, length = 100)
	    private String nmUsuario;

	    @Column(name = "nm_login", nullable = false, length = 100)
	    private String nmLogin;

	    @Column(name = "nm_senha", nullable = false, length = 100)
	    private String nmSenha;

	    @Column(name = "nm_email", nullable = false, length = 300)
	    private String nmEmail;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "role")
	    private UsuarioRole role;
	    
	    public UsuarioModel(String nmUsuario, String nmLogin, String nmSenha, String nmEmail, UsuarioRole role) {
	    	this.nmUsuario = nmUsuario;
	    	this.nmLogin = nmLogin;
	    	this.nmSenha = nmSenha;
	    	this.nmEmail = nmEmail;
	    	this.role = role;
	    	
	    }

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			if(this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
			else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
			
		}

		@Override
		public String getPassword() {
			return nmSenha;
		}

		@Override
		public String getUsername() {
			return nmLogin;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		


}
