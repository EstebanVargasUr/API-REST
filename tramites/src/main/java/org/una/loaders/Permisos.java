package org.una.loaders;

/**
 *
 * @author Esteban Vargas
 */
public enum Permisos {
    CrearUsuario("USU1"),
    ModificarUsuario("USU2"),
    InactivarUsuario("USU3"),
    ConsultarUsuario("USU4"),
    CrearDepartamento("DEP1"),
    ModificarDepartamento("DEP2"),
    InactivarDepartamento("DEP3"),
    ConsultarDepartamento("DEP4"),
    RegistrarTramite("TRA1"),
    ModificarTramite("TRA2"),
    InactivarTramite("TRA3"),
    FinalizarTramite("TRA4"),
    ConsultarTramite("TRA5"),
    ConsultarTodosTramites("TRA6"),
    DiseñarTramitesVariacionesRequisitos("TRD1"),
    ConsultarReportesTransacciones("TRU1"),
    ConsultarReportesAlertasFavoritismo("TRU2"),
    RegistrarTramitePropio("TRP1"),
    ConsultarTramitePropio("TRP2"),
    ConsultarReporteTramiteUsuario("TRU3"),
    ConsultarReporteTramiteFecha("TRU4"),
    ConsultarReporteTramiteTipo("TRU5"),
    ConsultarReporteTramiteEstado("TRU6"),
    ConsultarReporteTramiteDepartamento("TRU7"),
    ConsultarReporteTramiteCliente("TRU8");
//TODO: completar esta lista
    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
