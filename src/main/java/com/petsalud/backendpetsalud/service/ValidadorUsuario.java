package com.petsalud.backendpetsalud.service;

import java.util.ArrayList;
import java.util.List;

import com.petsalud.backendpetsalud.entity.Usuario;

/**
 * Valida que un usuario tenga registrados sus datos obligatorios
 * (nombre, apellido, email y dirección) antes de realizar acciones como una venta.
 */
public final class ValidadorUsuario {

    private ValidadorUsuario() {
        // Clase utilitaria: no se instancia
    }

    /** Devuelve los campos obligatorios que faltan. Lista vacía = usuario completo. */
    public static List<String> camposFaltantes(Usuario usuario) {
        List<String> faltantes = new ArrayList<>();
        if (estaVacio(usuario.getNombre())) faltantes.add("nombre");
        if (estaVacio(usuario.getApellido())) faltantes.add("apellido");
        if (estaVacio(usuario.getEmail())) faltantes.add("email");
        if (estaVacio(usuario.getDireccion())) faltantes.add("direccion");
        return faltantes;
    }

    /** Lanza IllegalArgumentException (el GlobalExceptionHandler la convierte en 400) si faltan datos. */
    public static void validarDatosCompletos(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
        List<String> faltantes = camposFaltantes(usuario);
        if (!faltantes.isEmpty()) {
            throw new IllegalArgumentException(
                    "El usuario no tiene sus datos obligatorios completos. Faltan: " + String.join(", ", faltantes));
        }
    }

    private static boolean estaVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}