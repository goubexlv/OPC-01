import java.util.Scanner

data class Clients(val nomClient: String, val numCompteur: Int, var consommation: Double, var netPayer:Double = 0.0)
class consommationanormalexeption(message: String) : Exception(message)

fun List<Double>.moyenneConsommations(): Double {
    if (this.isEmpty()) return 0.0
    return this.sum() / this.size
}

fun main() {
    var nomClient : String?
    var numCompteur :Int?
    var consommation : Double
    val tauxConsommation : Int = 50

    var choix1 = true
    var choix :Int

    println("###########################################")
    println("Bienvenue à ENEO !")
    println("###########################################")

    val listeClients = mutableListOf(
        Clients("junior", 123456, 40.0)
       )


    if(verifi_consommation(listeClients[0].consommation)) {
        listeClients[0].netPayer = tauxConsommation*listeClients[0].consommation
    }
    var listeConsommationClient = listOf(listeClients[0].netPayer)
    println(listeConsommationClient.moyenneConsommations())

    do{
        println("1- Ajouter un client\n" +
                "2- Calculer les factures avec précision \n" +
                "3- Détecter les anomalies de consommation\n" +
                "4- Fournir un résumé global de la consommation des clients")

        choix = 1
        when(choix){
            1 -> listeClients.add(CreeClient())
            2 -> {
                println("###########################################")
                println("Calculer les factures avec précision")
                println("###########################################")
                for(i in 0..listeClients.size){
                    if(verifi_consommation(listeClients[i].consommation)) {
                        listeClients[i].netPayer = tauxConsommation*listeClients[i].consommation
                    }
                }
            }

            3 -> {
                println("###########################################")
                println("Détecter les anomalies de consommation")
                println("###########################################")
                for(i in 0..listeClients.size){
                    println("Client $i\n" +
                            "Nom : ${listeClients[i].netPayer}\n" +
                            "facturation : ${listeClients[i].netPayer} XAF\n" +
                            "Anormali : ${Detection_anomalie(listeClients[i].netPayer)}")
                    println("///////////////////////////////////////////")

                }
            }
            else -> println("Choix non disponible")
        }

    }while(choix1 == true)

}

fun verifi_consommation(consommation : Double ) : Boolean{

    if(consommation == 0.0){
        println("Aucune consommation detecter")
        return false
    }

    if(consommation > 1000){
        println("Alerte consommation tres eleve")

    }
    if(consommation < 0){
        println("Alerte consommation tres eleve")
        return false
    }

    return true
}

fun CreeClient() : Clients {
    var nomClient : String?
    var numCompteur :Int?
    var consommation : Double

    println("###########################################")
    println("Creation client !")
    println("###########################################")

    val console  = Scanner(System.`in`)
    println("Entrez le nom client :")
    nomClient = console.nextLine()
    println("Entrez le nom client :")
    numCompteur = console.nextInt()
    println("Entrez le nom client :")
    consommation = console.nextDouble()

    return Clients(nomClient, numCompteur, consommation)
}

fun Detection_anomalie(consommation : Double){

    return try {
        if (consommation > 50) {
            throw consommationanormalexeption("La consommation $consommation est anormale.")
        }
        println("La consommation $consommation est valide.")
    } catch (e: consommationanormalexeption) {
        println("Erreur : ${e.message}")
    } finally {
        println("Vérification terminée.")
    }

}

fun afficherClient(listClients: List<Any>){
    println("Liste client")
    for(i in 1..listClients.size){
        println("$i  - ${listClients[0] }")
    }
}

