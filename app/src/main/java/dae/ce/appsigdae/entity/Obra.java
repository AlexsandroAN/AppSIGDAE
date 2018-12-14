package dae.ce.appsigdae.entity;

import java.io.Serializable;

/**
 * Created by 39091 on 02/08/2017.
 */

public class Obra implements Serializable {

    private Integer id;

    private String codigoObra;

    private String descricaoObra;

    private String municipio;

    private String statusObra;

    private String contratanteNomeFantasia;

    private Double valorAtual;

    private Double totalExecutado;

    private Double saldoAMedir;

    private String contratadaNomeFantasia;

    private String matriculaFiscal;

    public Obra() {

    }

    public Obra(Integer id, String codigoObra, String descricaoObra, String municipio, String statusObra, String contratanteNomeFantasia, Double valorAtual, Double totalExecutado, Double saldoAMedir, String contratadaNomeFantasia, String matriculaFiscal) {
        this.id = id;
        this.codigoObra = codigoObra;
        this.descricaoObra = descricaoObra;
        this.municipio = municipio;
        this.statusObra = statusObra;
        this.contratanteNomeFantasia = contratanteNomeFantasia;
        this.valorAtual = valorAtual;
        this.totalExecutado = totalExecutado;
        this.saldoAMedir = saldoAMedir;
        this.contratadaNomeFantasia = contratadaNomeFantasia;
        this.contratadaNomeFantasia = contratadaNomeFantasia;
        this.matriculaFiscal = matriculaFiscal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoObra() {
        return codigoObra;
    }

    public void setCodigoObra(String codigoObra) {
        this.codigoObra = codigoObra;
    }

    public String getDescricaoObra() {
        return descricaoObra;
    }

    public void setDescricaoObra(String descricaoObra) {
        this.descricaoObra = descricaoObra;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getStatusObra() {
        return statusObra;
    }

    public void setStatusObra(String statusObra) {
        this.statusObra = statusObra;
    }

    public String getContratanteNomeFantasia() {
        return contratanteNomeFantasia;
    }

    public void setContratanteNomeFantasia(String contratanteNomeFantasia) {
        this.contratanteNomeFantasia = contratanteNomeFantasia;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getTotalExecutado() {
        return totalExecutado;
    }

    public void setTotalExecutado(Double totalExecutado) {
        this.totalExecutado = totalExecutado;
    }

    public Double getSaldoAMedir() {
        return saldoAMedir;
    }

    public void setSaldoAMedir(Double saldoAMedir) {
        this.saldoAMedir = saldoAMedir;
    }

    public String getContratadaNomeFantasia() {
        return contratadaNomeFantasia;
    }

    public void setContratadaNomeFantasia(String contratadaNomeFantasia) {
        this.contratadaNomeFantasia = contratadaNomeFantasia;
    }

    public String getMatriculaFiscal() {
        return matriculaFiscal;
    }

    public void setMatriculaFiscal(String matriculaFiscal) {
        this.matriculaFiscal = matriculaFiscal;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "id=" + id +
                ", codigoObra='" + codigoObra + '\'' +
                ", descricaoObra='" + descricaoObra + '\'' +
                ", municipio='" + municipio + '\'' +
                ", statusObra='" + statusObra + '\'' +
                ", contratanteNomeFantasia='" + contratanteNomeFantasia + '\'' +
                ", valorAtual=" + valorAtual +
                ", totalExecutado=" + totalExecutado +
                ", saldoAMedir=" + saldoAMedir +
                ", contratadaNomeFantasia=" + contratadaNomeFantasia +
                ", matriculaFiscal='" + matriculaFiscal + '\'' +
                '}';
    }
}
