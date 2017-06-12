/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.senac.pi3.db.dao;
/*
import br.com.senac.pi3.model.relatorios.RelatorioCliente;
import br.com.senac.pi3.model.relatorios.RelatorioFilial;
import br.com.senac.pi3.model.relatorios.RelatorioVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
*/
/**
 *
 * @author Nataly
 */
public class DaoRelatorio {
}
/*
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

    public DaoRelatorio(Connection conBanco) {
        this.conBanco = conBanco;
    }

    // --------------------------- Relatorios de Clientes --------------------------- //
    public RelatorioCliente buscaClientePorId(int idCliente) throws SQLException {
        String sql = "SELECT ID_CLIENTE, NOME, DT_CADASTRO, ENABLED, (SELECT COUNT(*) FROM VENDA "
                + "WHERE CLIENTE_ID = ?) AS QUANT_COMPRAS FROM CLIENTE WHERE ID_CLIENTE = ?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idCliente);
        ResultSet rs = psComando.executeQuery();

        RelatorioCliente cliente = new RelatorioCliente();

        while (rs.next()) {

            cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
            cliente.setDtCadastro(rs.getString("DT_CADASTRO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setSobrenome(rs.getString("SOBRENOME"));
            cliente.setDtNascimento(rs.getString("DT_NASC"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setSexo(rs.getString("SEXO"));
            cliente.setCelular(rs.getString("CEL"));
            cliente.setEmail(rs.getString("EMAIL"));
            cliente.setQtdCompras(rs.getInt("QUANT_COMPRAS"));
        }

        return cliente;
    }

    public ArrayList<RelatorioCliente> RelatorioCliente() throws SQLException {
        String sql = "SELECT ID_CLIENTE, NOME, DT_CADASTRO, ENABLED, (SELECT COUNT(*) "
                + "FROM VENDA WHERE CLIENTE_ID = ID_CLIENTE) AS QUANT_COMPRAS FROM CLIENTE";
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs = psComando.executeQuery();

        ArrayList<RelatorioCliente> listaClientes = new ArrayList<>();

        while (rs.next()) {
            RelatorioCliente cliente = new RelatorioCliente();

            cliente.setIdCliente(rs.getInt("ID_CLIENTE"));
            cliente.setDtCadastro(rs.getString("DT_CADASTRO"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setSobrenome(rs.getString("SOBRENOME"));
            cliente.setDtNascimento(rs.getString("DT_NASC"));
            cliente.setCpf(rs.getString("CPF"));
            cliente.setSexo(rs.getString("SEXO"));
            cliente.setCelular(rs.getString("CEL"));
            cliente.setEmail(rs.getString("EMAIL"));
            cliente.setQtdCompras(rs.getInt("QUANT_COMPRAS"));

            listaClientes.add(cliente);
        }

        return listaClientes;
    }

    // --------------------------- Relatorios de Filial --------------------------- //
    public RelatorioFilial buscaFilialPorId(int idFilial) throws SQLException {
        String sql = "SELECT CNPJ, DESC_NOME, DESC_FANTASIA, (SELECT COUNT(*) FROM VENDA WHERE FILIAL_ID = ?) "
                + "AS QUANT_VENDAS FROM FILIAL WHERE ID_FILIAL = ?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idFilial);
        ResultSet rs = psComando.executeQuery();

        RelatorioFilial filial = new RelatorioFilial();

        while (rs.next()) {

            filial.setCnpj(rs.getString("CNPJ"));
            filial.setDescNome(rs.getString("DESC_NOME"));
            filial.setDescFantasia(rs.getString("DESC_FANTASIA"));
            filial.setTelefone(rs.getString("DT_NASC"));
            filial.setQtdVendas(rs.getInt("QUANT_VENDAS"));

        }

        return filial;
    }

    public ArrayList<RelatorioFilial> RelatorioFilial() throws SQLException {
        String sql = "SELECT CNPJ, DESC_NOME, DESC_FANTASIA, (SELECT COUNT(*) FROM VENDA) AS"
                + " QUANT_VENDAS FROM FILIAL";
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs = psComando.executeQuery();

        ArrayList<RelatorioFilial> listaFiliais = new ArrayList<>();

        while (rs.next()) {
            RelatorioFilial filiais = new RelatorioFilial();

            filiais.setCnpj(rs.getString("CNPJ"));
            filiais.setDescNome(rs.getString("DESC_NOME"));
            filiais.setDescFantasia(rs.getString("DESC_FANTASIA"));
            filiais.setTelefone(rs.getString("DT_NASC"));
            filiais.setQtdVendas(rs.getInt("QUANT_VENDAS"));

            listaFiliais.add(filiais);
        }

        return listaFiliais;
    }

    // --------------------------- Relatorios de Vendas --------------------------- //
    public ArrayList<RelatorioVenda> relatorioVendas(String dtInicial, String dtFinal) throws SQLException {
        String sql = "SELECT VD.ID_VENDA, V.DT_VENDA, V.VALOR_COMPRA, VD.QTDUND, F.DESC_FANTASIA, C.NOME\n"
                + "FROM VENDA_PRODUTO VD\n"
                + "INNER JOIN VENDA V ON VD.ID_VENDA = V.ID_VENDA\n"
                + "INNER JOIN FILIAL F ON V.FILIAL_ID = F.ID_FILIAL\n"
                + "INNER JOIN CLIENTE C ON V.CLIENTE_ID = C.ID_CLIENTE\n"
                + "WHERE V.DT_VENDA BETWEEN" +dtInicial+ " AND "+ dtFinal;
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs = psComando.executeQuery();

        ArrayList<RelatorioVenda> listaVendas = new ArrayList<>();

        while (rs.next()) {
            RelatorioVenda vendas = new RelatorioVenda();

            vendas.setIdVenda(rs.getInt("idVenda"));
            vendas.setDtVenda(rs.getString("dtVenda"));
            vendas.setNomeCliente(rs.getString("DESC_FANTASIA"));
            vendas.setNomeFilial(rs.getString("DT_NASC"));
            vendas.setNomeProduto(rs.getString("NOME_PRODUTO"));
            vendas.setQtdProduto(rs.getInt("QTD_PRODUTO"));
            vendas.setValorVenda(rs.getDouble("VALOR_VENDA"));

            listaVendas.add(vendas);
        }
        return listaVendas;
    }
}
*/