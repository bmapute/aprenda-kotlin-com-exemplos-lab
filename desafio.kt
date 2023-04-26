enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(val nome:String, val email:String){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Usuario) return false
        if (email != other.email) return false
        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 30)

data class Formacao(val nome: String, val nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

   private val _inscritos = mutableListOf<Usuario>()
    val inscritos: List<Usuario> get() = _inscritos

    fun matricular(vararg usuarios: Usuario) {
        // A ideia do forEach seria para aplicar as regras de negocio para cada aluno antes de adicionar a coleção
        usuarios.forEach {
            if (!_inscritos.contains(it)) _inscritos.add(it)
        }
    }

}

fun main() {

    val conteudos=mutableListOf(
        ConteudoEducacional("Conhecendo o Kotlin e Sua Documentação oficial"),
        ConteudoEducacional("Introdução prática à Linguagem de Programação Kotlin",60),
        ConteudoEducacional("Estruturas de Controle de Fluxo e Coleções em Kotlin",90)
    )

     Formacao("Dominando a Linguagem de Programação Kotlin", Nivel.INTERMEDIARIO, conteudos)
        .apply {
            matricular(
                Usuario("bmapute", "email@test.com"),
                Usuario("jCossa", "email@test.com"))
        }.run {
            println("Usuarios Inscritos na Formação : ${nome} ->  ${inscritos}")
        }
    }