package org.openjfx.bashMe.Exceptions;



public class ExecutePermissionException extends RuntimeException{

  public ExecutePermissionException() {
    super();
  }

  public ExecutePermissionException(String message) {
    super(message);
  }
}

