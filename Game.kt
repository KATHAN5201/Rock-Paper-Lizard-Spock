/*Rules:
Scissors cuts Paper
Paper covers Rock
Rock crushes Lizard
Lizard poisons Spock
Spock smashes Scissors
Scissors decapitates Lizard
Lizard eats Paper
Paper disproves Spock
Spock vaporizes Rock
Rock crushes Scissors
 */

fun main() {
    val options = arrayOf("Rock", "Paper", "Scissors", "Lizard", "Spock")
    var gameChoice: String
    var userChoice: String

    //scores[0] --> computer ,scores[1] --> player, scores[3] --> Tie Count
    val scores = arrayOf(0, 0, 0)
    var gameOn = true

    println("Let's play Rock 🥌,Paper 📰, Scissors ✂, Lizard 🦎, Spock 🖖!")
    println("Press Q to quit and enter to play again")
    while (gameOn) {
        gameChoice = getGameChoice(options)
        userChoice = getUserChoice(options)

        //updating scores
        scores[printWinner(userChoice,gameChoice)]++

        println("Play again?")
        gameOn = readLine()?.toUpperCase() != "Q"

    }
    println("Thank you for playing")
    printFinalScores(scores)

}

//function to get game choice
fun getGameChoice(options: Array<String>) = options[(options.indices).random()]

//function to get user choice
fun getUserChoice(options: Array<String>): String {

    //there is a chance tha this may contain "null"
    //that's why declared as nullable String type
    var userInput: String? = null

    while (userInput !in options) {

        println("Please enter one of the following:")
        for (option in options) print(" $option")
        println(".")

        //as readLine() returns String? ,we use type safe dot "." operator
        userInput = readLine()?.capitalize()

        if (userInput !in options) println("You must enter a valid choice!")

    }

    //converting the type to non nullable String
    return userInput.toString()
}

//function to print winner of a trial and return Int whic is index of corresponding winner
/*
0-computer
1-player
2-Tie
 */
fun printWinner(userInput: String, gameInput: String): Int {
    if (userInput == gameInput) {
        println("We both chose $userInput ,It's a tie")
        return 2
    }

    val userWon =
        when (userInput) {
            "Rock" -> when (gameInput) {
                "Scissors" -> true
                "Lizard" -> true
                else -> false //spock,paper
            }
            "Paper" -> when (gameInput) {
                "Rock" -> true
                "Spock" -> true
                else -> false //scissors,lizard

            }
            "Scissor" -> when (gameInput) {
                "Paper" -> true
                "Lizard" -> true
                else -> false//rock,spock
            }
            "Lizard" -> when (gameInput) {
                "Paper" -> true
                "Spock" -> true
                else -> false //scissors,rock
            }
            "Spock" -> when (gameInput) {
                "Rock" -> true
                "Scissors" -> true
                else -> false//lizard,paper
            }
            else -> false
        }
    return if (userWon) {
        println("You chose $userInput ,I chose $gameInput,You won!")
        1
    } else {
        println("You chose $userInput ,I chose $gameInput,You Lose!")
        0
    }
}

//function to print final scores
fun printFinalScores(scores: Array<Int>) {
    println("---------Final Scores-------------")
    println("Me:${scores[0]}")
    println("You: ${scores[1]}")
    println("Ties: ${scores[2]}")

    when {
        scores[0] > scores[1] -> print("I won the round!")
        scores[0] < scores[1] -> println("You won the round!")
        else -> println("Seems we have a Draw!")
    }
    println("---------------------------------\n")

}