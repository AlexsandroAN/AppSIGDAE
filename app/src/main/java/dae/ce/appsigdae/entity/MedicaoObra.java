package dae.ce.appsigdae.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 39091 on 02/08/2017.
 */

public class MedicaoObra implements Serializable {

    private Integer id;

    private String codigoObra;

    private String nrMedicao;

    private Date dataInicio;

    private Date dataFim;

    private Double valorMedido;

    private String status;

    private byte[] foto;

    public MedicaoObra() {

    }

    public MedicaoObra(Integer id, String codigoObra, String nrMedicao, Date dataInicio, Date dataFim, Double valorMedido, String status) {
        this.id = id;
        this.codigoObra = codigoObra;
        this.nrMedicao = nrMedicao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorMedido = valorMedido;
        this.status = status;
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

    public String getNrMedicao() {
        return nrMedicao;
    }

    public void setNrMedicao(String nrMedicao) {
        this.nrMedicao = nrMedicao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Double getValorMedido() {
        return valorMedido;
    }

    public void setValorMedido(Double valorMedido) {
        this.valorMedido = valorMedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MedicaoObra{" +
                "id=" + id +
                '}';
    }
}
