package br.edu.unisep.cadastro.view;

import br.edu.unisep.cadastro.controller.UsuarioController;
import br.edu.unisep.cadastro.model.Usuario;

import java.util.Scanner;

public class UsuarioView {
    private UsuarioController controller;
    private Scanner scanner;

    public UsuarioView() {
        controller = new UsuarioController();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Editar Usuário");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    editarUsuario();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Tipo (administrador/cliente): ");
        String tipo = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, senha, tipo);
        controller.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void listarUsuarios() {
        System.out.println("\nLista de Usuários:");
        for (Usuario usuario : controller.listarUsuarios()) {
            System.out.println(usuario);
        }
    }

    private void editarUsuario() {
        System.out.print("Digite o e-mail do usuário a ser editado: ");
        String email = scanner.nextLine();

        Usuario usuario = controller.buscarUsuarioPorEmail(email);
        if (usuario != null) {
            System.out.print("Novo Nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo E-mail: ");
            String novoEmail = scanner.nextLine();
            System.out.print("Nova Senha: ");
            String novaSenha = scanner.nextLine();
            System.out.print("Novo Tipo (administrador/cliente): ");
            String novoTipo = scanner.nextLine();

            controller.editarUsuario(usuario, novoNome, novoEmail, novaSenha, novoTipo);
            System.out.println("Usuário editado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
}
