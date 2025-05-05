import java.util.Calendar

val expressaoRegular =  Regex("[0-5]")

//Variavel Global
//Instância de um lista mutável vazia
var listaConvidados : MutableList<Convidado> = mutableListOf<Convidado>()
val lista = mutableListOf("b", "a", "c", "e", "d")


fun main() {
    val i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)


    println(i)
    menu()
}

private fun menu(){
    do{
        println("--- MENU ---")
        println("1- CADASTRAR")
        println("2- LISTAR")
        println("3- EDITAR")
        println("4- EXCLUIR")
        println("5- BUSCA")
        println("0- SAIR")
        val opcao = readln()//Aqui precisa ser String
        //por causa do REGEX

        if (expressaoRegular.matches(opcao)){
            when (opcao.toInt()) {
                1 -> {
                    println("Cadastrando...")
                    cadastrar()
                }

                2 -> {
                    println("Listando...")
                    listar()
                }

                3 -> {
                    println("Editando...")
                    editar()
                }

                4 -> {
                    println("Excluindo...")
                    excluir()
                }

                5 -> {
                    println("Buscando...")
                    busca()
                }

                0 -> print("Saindo")
            }
        } else{
            println("\n\n\nOpção inválida")
        }

    }while(opcao != "0")//PRECISA SER STRING!
}

//QUESTÃO 1 - Valida para que o usuario
//somente digite LETRAS para escrever o nome
private fun cadastrar() {
    val regexLetras = Regex("^[A-Za-zÀ-ÿ ]+$")
    // Aceita letras (maiúsculas/minúsculas), acentos e espaços
    var nome: String

    while (true) {
        print("Qual o seu nome? ")
        nome = readln()
        if (regexLetras.matches(nome)) {
            break
        } else {
            println("Nome inválido! Digite apenas letras.")
        }
    }

    print("Qual vai ser o presente? ")
    val presente = readln()

    print("Qual sua restrição alimentar? ")
    val alimento = readln()

    val convidado = Convidado()
    convidado.nome = nome
    convidado.presente = presente
    convidado.alimentar = alimento

    listaConvidados.add(convidado)

}

/*fun algumaCoisa() : tipo esperasse que a função
* receba esse tipo no final dela com um return
* */
private fun listar() : String{
    var i = 0

    listaConvidados.sortBy {
        it.nome
    }

    if (validar()){
        listaConvidados.forEach { convidado ->
            print(
                "Posição: ${i++}" +
                        "Nome: ${convidado.nome}; " +
                        "Presente: ${convidado.presente} ; " +
                        "Restrição: ${convidado.alimentar}; " +
                        "Vai ir a festa? ${convidado.presenca} \n"
            )//FIM do PRINT
        }//FIM do FOREACH
    }//FIM do IF
    return "Listagem foi um sucesso"//esse é o meu retorna da função
}//FIM da FUNÇÃO LISTAR

/*QUESTÃO 2 - Validar para que a variavel posição seja sempre
numérica e a variavel resposta seja sempre "S" ou "N"*/
private fun editar(): Boolean {
    if (validar()) {
        listar()

        var posicao: Int
        while (true) {
            print("Digite a posição a ser editada: ")
            val inputPosicao = readln()
            if (inputPosicao.all { it.isDigit() }) {
                posicao = inputPosicao.toInt()
                if (posicao in listaConvidados.indices) {
                    break
                } else {
                    println("Posição inválida! Fora dos limites da lista.")
                }
            } else {
                println("Entrada inválida! Digite apenas números.")
            }
        }

        var resposta: String
        while (true) {
            print("O convidado vai? (S/N): ")
            resposta = readln().trim().uppercase()
            if (resposta == "S" || resposta == "N") {
                break
            } else {
                println("Resposta inválida! Digite apenas S ou N.")
            }
        }

        listaConvidados[posicao].presenca = resposta == "S"
        println("Presença atualizada com sucesso.")
        return true
    }

    println("Edição cancelada.")
    return false
}
private fun excluir(): Boolean {
    if (validar()) {
        listar()

        var posicao: Int
        while (true) {
            print("Qual posição você deseja remover: ")
            val inputPosicao = readln()
            if (inputPosicao.all { it.isDigit() }) {
                posicao = inputPosicao.toInt()
                if (posicao in listaConvidados.indices) {
                    break
                } else {
                    println("Posição inválida! Fora dos limites da lista.")
                }
            } else {
                println("Entrada inválida! Digite apenas números.")
            }
        }

        listaConvidados.removeAt(posicao)
        println("Convidado excluído com sucesso.")
        return true
    }

    println("Exclusão cancelada. Lista vazia.")
    return false
}

/*QUESTÃO 4 - Validar para que a variavel busca seja sempre
alfabética, ignora letras maiusculas e minusculas*/
private fun busca(){
    var i = 0//indice da lista
    print("Digite o nome da pessoa que você busca: ")
    val busca = readln()
    listaConvidados.forEach { convidado ->
        //O contains busca por uma string dentro de uma outra string
        if (convidado.nome.contains(busca)){
            println("Posição: $i, Nome: ${convidado.nome}")
        }
        i++
    }
}

private fun validar() : Boolean{
    if(listaConvidados.isEmpty()){
        println("Lista vazia! Finalizando...")
        return false
    }
    return true
}