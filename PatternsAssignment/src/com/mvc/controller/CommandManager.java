package com.mvc.controller;

import java.util.Stack;

public class CommandManager {
	private Stack<Command> commandStack = new Stack<>();
	private int index = -1;
	
	private static CommandManager commandManager = null;
	
	private CommandManager(){
	}
	
	public static CommandManager getInstance() {
		if(commandManager == null) {
			commandManager = new CommandManager();
		}
		return commandManager;
	}
	
	public void addToCommandStack(Command command) {
		deleteCommandsAfterIndex();
		commandStack.push(command);
	    index++;
	}
	
	private void deleteCommandsAfterIndex() {
		if(commandStack.isEmpty()){
			return;
		}	
	    for(int i = commandStack.size()-1; i > index; i--)
	    {
	        commandStack.remove(i);
	    }
	}

	public void undo() {
		if(index >= 0){
			Command command = commandStack.get(index);
		    command.unexecute();
		    index--;
		}   
	}
	
	public void redo() {
		if(index == commandStack.size() - 1)
	        return;
	    index++;
	    Command command = commandStack.get(index);
	    command.execute();
	}
	
	public void clear(){
		commandStack.clear();
	}
}
