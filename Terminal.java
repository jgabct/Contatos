package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	public static Scanner in = new Scanner(System.in);
	public static String shortcut = "system_call_generate_contacts_auto";
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
					displaySubMenu();
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
					default:
						System.out.println("========================");
						System.out.println("Não é um opção!!!");
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
				if (contato != null && !contato.isEmpty()) {
					deleteSubMenu();
					System.out.print("Opção: ");
					op = in.nextInt();
					switch (op) {
					case 1:
						deleteContato(contato, 1);
						break;
					case 2:
						deleteContato(contato, 2);
						break;
					default:
						System.out.println("========================");
						System.out.println("Não é um opção!!!");
						break;
					}
				} else {
					System.out.println("========================");
					System.out.println("Não há contatos ainda");
				}
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("========================");
				System.out.println("Não é um opção!!!");
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

	public static void displaySubMenu() {
		System.out.println("========================");
		System.out.println("         Mostrar        ");
		System.out.println("========================");
		System.out.println("[ 1 ] Nome              ");
		System.out.println("[ 2 ] Local de Trabalho ");
		System.out.println("[ 3 ] Todos             ");
		System.out.println("========================");
	}

	public static void deleteSubMenu() {
		System.out.println("========================");
		System.out.println("         Deletar        ");
		System.out.println("========================");
		System.out.println("[ 1 ] Nome              ");
		System.out.println("[ 2 ] Lista de Contatos ");
		System.out.println("========================");
	}

	public static void novoContato(ArrayList<Contato> ctt) throws IOException {
		System.out.println("========================");
		System.out.println("         Contato        ");
		System.out.println("========================");
		System.out.print("Nome: ");
		name = in.next().toLowerCase();
		if (!name.equals(shortcut)) {
			while (!testName(name, ctt)) {
				System.out.print("Nome já exitente, insira novamente: ");
				name = in.next().toLowerCase();
			}
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
		} else {
			autoFillContactList(ctt);
		}
	}

	public static void listContatos(ArrayList<Contato> ctt, int op) {
		if (op == 1) {
			System.out.print("Informe um nome: ");
			nameId = in.next().toLowerCase();
			int id = -1, i = 0;
			for (Contato c : ctt) {
				if (nameId.equalsIgnoreCase(c.getName())) {
					id = i;
					break;
				} else {
					i++;
				}
			}
			if (i != ctt.size()) {
				System.out.println("------------------------");
				System.out.printf("Nome: %s\n", capitalize(ctt.get(id).getName()));
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
			boolean[] idList = new boolean[ctt.size()];
			int i = 0;
			for (Contato c : ctt) {
				if (nameId.equalsIgnoreCase(c.getLocalDeTrabalho())) {
					idList[i] = true;
					i++;
				} else {
					idList[i] = false;
					i++;
				}
			}
			for (int l = 0; l < idList.length; l++) {
				if (idList[l]) {
					System.out.println("------------------------");
					System.out.printf("Nome: %s\n", capitalize(ctt.get(l).getName()));
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
				System.out.printf("Nome: %s\n", capitalize(c.getName()));
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
			nameId = in.next().toLowerCase();
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

	public static void deleteContato(ArrayList<Contato> ctt, int op) {
		if(op == 1) {
			System.out.print("Informe o nome para a deletar: ");
			String nameId = in.next().toLowerCase();
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
		}else if (op == 2) {
			int key = 0;
			System.out.println("========================");
			for (Contato c : ctt) {
				System.out.println("["+(key+1)+"] "+ capitalize(c.getName()));
				key++;
			}
			System.out.print("Opção: ");
			key = in.nextInt()-1;
			if(key>=0&&key<ctt.size()) {
				ctt.remove(key);
				System.out.println("Deletado com sucesso!!!");
			}else {
				System.out.println("Operação interrompida");
			}
		}
	}

	public static void autoFillContactList(ArrayList<Contato> ctt) throws IOException {
		String[] dados = fileReader("src/app/contatos.txt").split(";");
		int l = 0;
		for (int i = 0; i < (dados.length / 7); i++) {
			if (testName(dados[l], ctt)) {
				ctt.add(new Contato(dados[l].toLowerCase(), Integer.parseInt(dados[(l + 1)]), Integer.parseInt(dados[(l + 2)]),
						dados[(l + 3)], dados[(l + 4)], dados[l + 5], dados[l + 6]));
				l += 7;
			} else {
				l += 7;
			}
		}
	}

	public static String fileReader(String stg) throws IOException {
		FileReader arq = new FileReader(stg);
		BufferedReader lerArq = new BufferedReader(arq);
		String linha = "", inlineText = "";
		linha = lerArq.readLine();
		while (linha != null) {
			inlineText += linha;
			linha = lerArq.readLine();
		}
		return inlineText;
	}

	public static boolean testName(String name, ArrayList<Contato> ctt) {
		boolean valid = true;
		for (int i = 0; i < ctt.size(); i++) {
			if (name.equalsIgnoreCase(ctt.get(i).getName())) {
				valid = false;
				break;
			}
		}
		return valid;
	}

	public static String capitalize(String text) {
		return text.substring(0,1).toUpperCase() + text.substring(1);
	}
}
