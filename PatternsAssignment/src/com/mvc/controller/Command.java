package com.mvc.controller;

import com.mvc.model.Memento;

public abstract class Command {
	Memento memento;
	
	public abstract void execute();
	public abstract void unexecute();
}
