package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	public static Scanner in = new Scanner(System.in);
	public static String name, endereco, email, instagram, local_de_trabalho, nameId;
	public static int op, celular, telefone;

	public static void main(String[] args) throws IOException {
		boolean exit = false;
		ArrayList<Contato> contato = new ArrayList<Contato>();
		while (true) {
			menu();
			System.out.print("Opção: ");
			op = in.nextInt();
			switch (op) {
			case 1:
				novoContato(contato);
				break;
			case 2:
				if (contato != null && !contato.isEmpty()) {
					subMenu();
					System.out.print("Opção: ");
					op = in.nextInt();
					switch (op) {
					case 1:
						listContatos(contato, 1);
						break;
					case 2:
						listContatos(contato, 2);
						break;
					case 3:
						listContatos(contato, 3);
						break;
					}
				} else {
					System.out.println("========================");
					System.out.println("Não há contatos ainda");
				}
				break;
			case 3:
				editContato(contato);
				break;
			case 4:
				deleteContato(contato);
				break;
			case 5:
				exit = true;
				break;
			default:
				break;
			}
			if (exit)
				break;
		}

	}

	public static void menu() {
		System.out.println("========================");
		System.out.println("          Menu          ");
		System.out.println("========================");
		System.out.println("[ 1 ] Novo Contato      ");
		System.out.println("[ 2 ] Mostrar           ");
		System.out.println("[ 3 ] Editar            ");
		System.out.println("[ 4 ] Deletar           ");
		System.out.println("[ 5 ] Sair              ");
		System.out.println("========================");
	}

	public static void subMenu() {
		System.out.println("========================");
		System.out.println("         Mostrar        ");
		System.out.println("========================");
		System.out.println("[ 1 ] Nome              ");
		System.out.println("[ 2 ] Local de Trabalho ");
		System.out.println("[ 3 ] Todos             ");
		System.out.println("========================");
	}

	public static void novoContato(ArrayList<Contato> ctt) throws IOException {
		System.out.println("========================");
		System.out.println("         Contato        ");
		System.out.println("========================");
		System.out.print("Nome: ");
		name = in.next();
		if (name.equals("admin.fill.list")) {
			autoInsert(ctt);
		} else {
			System.out.print("Celular: ");
			celular = in.nextInt();
			System.out.print("Telefone: ");
			telefone = in.nextInt();
			System.out.print("E-mail: ");
			email = in.next();
			System.out.print("Instagram: ");
			instagram = in.next();
			System.out.print("Endereço: ");
			endereco = in.next();
			System.out.print("Local de Trabalho: ");
			local_de_trabalho = in.next();
			ctt.add(new Contato(name, celular, telefone, endereco, email, instagram, local_de_trabalho));
		}
	}

	public static void listContatos(ArrayList<Contato> ctt, int op) {
		if (op == 1) {
			System.out.print("Informe um nome: ");
			nameId = in.next();
			int id = -1, i = 0;
			for (Contato c : ctt) {
				if(nameId.equalsIgnoreCase(c.getName())) {
					id = i;
					break;
				}else {
					i++;
				}
			}
			if (i != ctt.size()) {
				System.out.println("------------------------");
				System.out.printf("Nome: %s\n", ctt.get(id).getName());
				System.out.printf("Celular: %s\n", ctt.get(id).getCelular());
				System.out.printf("Telefone: %s\n", ctt.get(id).getTelefone());
				System.out.printf("E-mail: %s\n", ctt.get(id).getEmail());
				System.out.printf("Instagram: %s\n", ctt.get(id).getInstagram());
				System.out.printf("Endereço: %s\n", ctt.get(id).getEndereco());
				System.out.printf("Local de Trabalho: %s\n", ctt.get(id).getLocalDeTrabalho());
			} else {
				System.out.println("Contato inexistente!!!");
			}
		} else if (op == 2) {
			System.out.print("Informe o nome da empressa: ");
			nameId = in.next();
			boolean [] idList = new boolean[ctt.size()];
			int i = 0;
			for (Contato c : ctt) {
				if(nameId.equalsIgnoreCase(c.getLocalDeTrabalho())) {
					idList[i] = true;
					i++;
				}else {
					idList[i] = false;
					i++;
				}
			}
			for (int l = 0; l < idList.length; l++) {
				if(idList[l]) {
					System.out.println("------------------------");
					System.out.printf("Nome: %s\n", ctt.get(l).getName());
					System.out.printf("Celular: %s\n", ctt.get(l).getCelular());
					System.out.printf("Telefone: %s\n", ctt.get(l).getTelefone());
					System.out.printf("E-mail: %s\n", ctt.get(l).getEmail());
					System.out.printf("Instagram: %s\n", ctt.get(l).getInstagram());
					System.out.printf("Endereço: %s\n", ctt.get(l).getEndereco());
					System.out.printf("Local de Trabalho: %s\n", ctt.get(l).getLocalDeTrabalho());
				}
			}
		} else if (op == 3) {
			for (Contato c : ctt) {
				System.out.println("------------------------");
				System.out.printf("Nome: %s\n", c.getName());
				System.out.printf("Celular: %s\n", c.getCelular());
				System.out.printf("Telefone: %s\n", c.getTelefone());
				System.out.printf("E-mail: %s\n", c.getEmail());
				System.out.printf("Instagram: %s\n", c.getInstagram());
				System.out.printf("Endereço: %s\n", c.getEndereco());
				System.out.printf("Local de Trabalho: %s\n", c.getLocalDeTrabalho());
			}
		}

	}

	public static void editContato(ArrayList<Contato> ctt) {
		if (ctt != null && !ctt.isEmpty()) {
			System.out.print("Informe o nome para a edição: ");
			nameId = in.next();
			int id = 0;
			for (int i = 0; i < ctt.size(); i++) {
				if (nameId.equalsIgnoreCase(ctt.get(i).getName())) {
					id = i;
					break;
				} else {
					id = -1;
				}
			}
			if (id != -1) {
				System.out.print("Celular: ");
				ctt.get(id).setCelular(in.nextInt());
				System.out.print("Telefone: ");
				ctt.get(id).setTelefone(in.nextInt());
				System.out.print("E-mail: ");
				ctt.get(id).setEmail(in.next());
				System.out.print("Instagram: ");
				ctt.get(id).setInstagram(in.next());
				System.out.print("Endereço: ");
				ctt.get(id).setEndereco(in.next());
				System.out.print("Local de Trabalho: ");
				ctt.get(id).setLocalDeTrabalho(in.next());
			} else {
				System.out.println("Contato não existe");
			}
		} else {
			System.out.println("========================");
			System.out.println("Não há contatos ainda");
		}
	}

	public static void deleteContato(ArrayList<Contato> ctt) {
		if (ctt != null && !ctt.isEmpty()) {
			System.out.print("Informe o nome para a deletar: ");
			String nameId = in.next();
			int id = 0;
			for (int i = 0; i < ctt.size(); i++) {
				if (nameId.equalsIgnoreCase(ctt.get(i).getName())) {
					ctt.remove(i);
					System.out.println("Deletado com sucesso!!!");
					break;
				} else {
					id = -1;
				}
			}
			if (id != 0) {
				System.out.println("O Contato não existe");
			}
		} else {
			System.out.println("========================");
			System.out.println("Não há contatos ainda");
		}
	}

	public static void autoInsert(ArrayList<Contato> ctt) throws IOException {
		try {
			FileReader arq = new FileReader("src/app/contatos.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = "";
			int i = 0;
			linha = lerArq.readLine();
			while (linha != null) {
				if (linha.substring(0, 2).equals(i + "|")) {
					if (i == 0)
						name = linha.substring(2);
					if (i == 1)
						celular = Integer.parseInt(linha.substring(2));
					if (i == 2)
						telefone = Integer.parseInt(linha.substring(2));
					if (i == 3)
						email = linha.substring(2);
					if (i == 4)
						instagram = linha.substring(2);
					if (i == 5)
						endereco = instagram = linha.substring(2);
					if (i == 6)
						local_de_trabalho = linha.substring(2);
					if (i == 6)
						ctt.add(new Contato(name, celular, telefone, endereco, email, instagram, local_de_trabalho));
					if (i == 6)
						i -= 6;
					else
						i++;
				}
				linha = lerArq.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
