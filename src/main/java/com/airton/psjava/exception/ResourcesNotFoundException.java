package com.airton.psjava.exception;

public class ResourcesNotFoundException extends RuntimeException{

    public ResourcesNotFoundException(Long id){
        super("Resouce not found Id " + id);
    }

    public ResourcesNotFoundException(String msg){
        super(msg);
    }

}
