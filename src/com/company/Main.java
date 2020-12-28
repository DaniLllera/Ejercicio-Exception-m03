package com.company;

import java.util.Scanner;

public class Main {

    //     **DATOS NECESARIOS PARA EJECUTAR EL CODIGO**

    //dni= 33980764R es necesario para el login
    //Numero de cuenta adiccional = 9876541P Necesario para ingresarle dinero y comprobar que todo esta correcto (ejercicios 4-5)
    // La opcion 5 es para comprobar que el 4 esta correcto
    // He añadido un saldo por defecto de 400 para poder ejecutar sin necesidad de ingresar dinero
    public static void main(String[] args) throws ClientAccountException, BankAccountException {

        Scanner scanner = new Scanner(System.in);
        CompteEstalvi secundaria = new CompteEstalvi("9876541P");

        System.out.println("Por favor introduce tus datos de login");
        System.out.println("----------------------------------------");

        System.out.println("Introduce el numero de tu cuenta (Ex: 234456)");
        String cuenta = scanner.next();
        CompteEstalvi compteEstalvi = new CompteEstalvi(cuenta);

        System.out.println("Introduce el Nombre");
        String nom = scanner.next();
        System.out.println("Introduce el Apellido");
        String apellido = scanner.next();
        System.out.println("Introduce el DNI");
        String dni = scanner.next();

        Client dani = new Client(nom, apellido, dni);
        Client pedro = new Client("Pedro", "Perez", "33980764R");

        System.out.println("Entrando a la cuenta...........");
        System.out.println(".....................................");
        System.out.println("\nNumero de la Cuenta: " + compteEstalvi.getNumCompte());
        compteEstalvi.addUser(dani);
        compteEstalvi.addUser(pedro);

        int numero;
        do {
            System.out.println("\nQue quieres hacer?");
            System.out.println("1. Ingresar Dinero");
            System.out.println("2. Sacar Dinero");
            System.out.println("3. Borrar Usuario");
            System.out.println("4. Transferencia");
            System.out.println("5. Dinero disponibe ambas cuentas");
            System.out.println("6. Salir");
            numero = scanner.nextInt();

            switch (numero) {
                case 1 -> {
                    System.out.println("Cuanto dinero quieres ingresar");
                    double dineroIngresar = scanner.nextDouble();
                    compteEstalvi.ingressar(dineroIngresar);
                    System.out.println(compteEstalvi.getSaldo());
                }
                case 2 -> {
                    System.out.println("Cuanto dinero quiere sacar");
                    double dineroSacar = scanner.nextDouble();
                    compteEstalvi.treure(dineroSacar);
                    System.out.println(compteEstalvi.getSaldo());
                }
                case 3 -> {
                    System.out.println("Que usuario quieres borrar");
                    for (int i = 0; i < compteEstalvi.getLlista_usuaris().size(); i++) {
                        System.out.println("Nombre: " + compteEstalvi.getLlista_usuaris().get(i).getNom());
                    }

                    System.out.println("\nIntroduce el nombre del usuario que quieres borrar");
                    String user = scanner.next();
                    compteEstalvi.removeUser(user);
                    for (int i = 0; i < compteEstalvi.getLlista_usuaris().size(); i++) {
                        System.out.println("Usuario Borrado.....");
                        System.out.println("Estos son los usuarios que quedan:");
                        System.out.println("Nombre: " + compteEstalvi.getLlista_usuaris().get(i).getNom());
                    }
                }
                case 4 -> {
                    System.out.println("Introduce a que cuenta quieres hacer la transferencia");
                    String cuentaIntroducida = scanner.next();
                    if (cuentaIntroducida.equals(secundaria.getNumCompte())) {
                        System.out.println("Este es tu saldo total " + compteEstalvi.getSaldo() + " euros, de cuanto quieres hacer la transferencia? ");
                        double saldoTransferencia = scanner.nextDouble();
                        if (cuentaIntroducida.equals(secundaria.getNumCompte())){
                            if (compteEstalvi.getSaldo() > saldoTransferencia) {
                                secundaria.ingressar(saldoTransferencia);
                                compteEstalvi.treure(saldoTransferencia);
                                System.out.println("Saldo Restante: " + compteEstalvi.getSaldo());
                            } else {
                                throw new BankAccountException(ExceptionMessage.TRANSFER_ERROR);
                            }
                        }

                    } else {
                        throw new BankAccountException(ExceptionMessage.ACCOUNT_NOT_FOUND);
                    }
                }
                case 5 -> {
                    System.out.println("Informacion de Cuentas");
                    System.out.println("Numero Compte: " + compteEstalvi.getNumCompte() + " Saldo: " + compteEstalvi.getSaldo());
                    System.out.println("Numero Compte secundaria: " + secundaria.getNumCompte() + " Saldo: " + secundaria.getSaldo());
                }

                case 6 -> System.out.println("Adios");
                default -> System.out.println("Introduce un numero entre 1-6");
            }


        } while (numero != 6);
    }
}
