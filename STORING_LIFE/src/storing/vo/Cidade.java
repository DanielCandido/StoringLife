/*
 * Cidade.java
 *
 * Classe model que representa uma cidade no sistema.
 *
 * © 2016 - Faculdades Opet - Todos os direitos reservados.
 *
 * Histórico
 * 14/07/2016 – Versão 1.0 - José Augusto – Criação do arquivo
 *
 */

package storing.vo;

import java.io.Serializable;

public class Cidade implements Serializable, Cloneable
{
    /* Atributos estáticos */
    private static final long serialVersionUID = 5608681969964154425L;

    /* Atributos normais */
    private int               codigo;
    private String            nome;

    /* Métodos de acesso */
    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int pCodigo)
    {
        codigo = pCodigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    /* Métodos da classe Object */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + codigo;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cidade other = (Cidade) obj;
        if (codigo != other.codigo)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(codigo);
        tBuilder.append("-");
        tBuilder.append(nome);
        return tBuilder.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
