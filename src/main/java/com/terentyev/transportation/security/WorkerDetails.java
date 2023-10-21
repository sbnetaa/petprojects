package com.terentyev.transportation.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.terentyev.transportation.entities.Worker;

public class WorkerDetails implements UserDetails {
	private final Worker worker;

	
	public WorkerDetails(Worker worker) {
		super();
		this.worker = worker;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (getWorker().isBoss()) return Collections.singletonList(new SimpleGrantedAuthority("ROLE_BOSS"));
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_DRIVER"));
	}

	@Override
	public String getPassword() {	
		return worker.getPassword();
	}

	@Override
	public String getUsername() {
		return worker.getUsername();
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
		// TODO Auto-generated method stub
		return true;
	}

	public Worker getWorker() {
		return worker;
	}
}
