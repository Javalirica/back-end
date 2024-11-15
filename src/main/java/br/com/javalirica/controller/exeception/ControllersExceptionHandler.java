package br.com.javalirica.controller.exeception;

import br.com.javalirica.service.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllersExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<StandardError> livroNaoEncontradoException (LivroNaoEncontradoException e , HttpServletRequest request){
        String error = " Livro não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(LeitorNaoEncontradoException.class)
    public ResponseEntity<StandardError> leitorNaoEncontradoException (LeitorNaoEncontradoException e , HttpServletRequest request){
        String error = " Leitor não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(EmprestimoNaoEncontradoException.class)
    public ResponseEntity<StandardError> emprestimoNaoEncontradoException (EmprestimoNaoEncontradoException e , HttpServletRequest request){
        String error = " Leitor não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBaseException (DataBaseException e , HttpServletRequest request) {
        String error = " Data Base error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(GerenciadorJaExistenteException.class)
    public ResponseEntity<StandardError> gerenciadorJaExistenteException (GerenciadorJaExistenteException e , HttpServletRequest request) {
        String error = " Gerenciador já existe  ";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(EmailJaExisteException.class)
    public ResponseEntity<StandardError> emailJaExisteException (EmailJaExisteException e , HttpServletRequest request){
        String error = " Email já cadastrado ";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(CpfInvalidException.class)
    public ResponseEntity<StandardError> cpfInvalidException (CpfInvalidException e , HttpServletRequest request) {
        String error = "Cpf invalido ";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(EmprestimoInvalidoException.class)
    public ResponseEntity<StandardError> emprestimoInvalidoException (EmprestimoInvalidoException e , HttpServletRequest request) {
        String error = "Não foi possivel realizar o emprestimo";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }

    @ExceptionHandler(RoleInvalidaException.class)
    public ResponseEntity<StandardError> resourceNotFound (RoleInvalidaException e , HttpServletRequest request){
        String error = " Role Invalida";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(LivroInvalidoException.class)
    public ResponseEntity<StandardError> livroInvalidoException (LivroInvalidoException e , HttpServletRequest request){
        String error = " Livro Invalida";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentialsException (BadCredentialsException e , HttpServletRequest request){
        String error = " erro ao fazer login ";
        HttpStatus status = HttpStatus.BAD_GATEWAY;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exceptionGeral (Exception e , HttpServletRequest request){
        String error = " error inesperado";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError stndError = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(stndError);
    }

}
