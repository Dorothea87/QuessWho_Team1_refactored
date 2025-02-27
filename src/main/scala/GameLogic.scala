import scala.annotation.tailrec
import scala.io.StdIn.readLine

object GameLogic extends App {

  /**
   * Initializes the game board with predefined characters and players.
   *
   * @return The initialized game board.
   */
  private def initializeBoard: Board = {
    val character1 = Character("Alice", Blue, Blonde, Red, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
    val character2 = Character("Bob", Brown, Black, Blue, Male, hasGlasses = false, hasBeard = true, hasHat = true, hasPet = false)
    val character3 = Character("Charlie", Green, Brown, Green, Male, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
    val character4 = Character("Diana", Hazel, Red, Yellow, Female, hasGlasses = false, hasBeard = false, hasHat = true, hasPet = false)
    val character5 = Character("Eve", Blue, Black, Purple, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
    val character6 = Character("Frank", Brown, Blonde, Orange, Male, hasGlasses = false, hasBeard = true, hasHat = true, hasPet = false)
    val character7 = Character("Grace", Green, Brown, Pink, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
    val character8 = Character("Hank", Hazel, Red, Blue, Male, hasGlasses = false, hasBeard = true, hasHat = true, hasPet = false)
    val character9 = Character("Ivy", Blue, Black, Green, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
    val character10 = Character("Jack", Brown, Blonde, Yellow, Male, hasGlasses = false, hasBeard = true, hasHat = true, hasPet = false)

    val player1: Player = Player("Andy")
    val player2: Player = Player("April")

    val board: Board = Board(List(character1, character2, character3, character4, character5, character6, character7, character8, character9, character10), player1, player2)

    board
  }

  /** The game board initialized with characters and players. */
  private val board: Board = initializeBoard

  /**
   * Prompts the user to guess the character's name and checks if the guess is correct.
   */
  private def guessNameOption(): Unit = {
    val nameGuess: String = readLine("Enter character's name: ").toLowerCase.capitalize

    if (board.guessCharacter(nameGuess)) {
      println(s"Congratulations, ${board.getCurrentPlayerName} you've won!")
      System.exit(0)
    } else {
      println("Try again, that wasn't the character's name.")
    }
  }

  /**
   * Prompts the user to ask a question about the character and checks if the answer is correct.
   */
  private def askQuestionOption(): Unit = {
    println("1. Is the character male?")
    println("2. Is the character female?")
    println("3. Does the character have glasses?")
    println("4. Does the character have a pet?")
    println("5. Does the character have a beard?")
    println("6. Does the character have a hat?")
    println("7. What is the character's hair color?")
    println("8. What is the character's eye color?")
    println("9. What is the character's jumper color?\n")

    val userQuestionChoice: String = readLine("Choose a question: ")

    if (userQuestionChoice == "1") {
      val response: Boolean = board.askQuestion("male")

      if (response) {
        println("Well done, the character is MALE!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "2") {
      val response: Boolean = board.askQuestion("female")

      if (response) {
        println("Well done, the character is FEMALE!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "3") {
      val response: Boolean = board.askQuestion("glasses")

      if (response) {
        println("Well done, the character has glasses!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "4") {
      val response: Boolean = board.askQuestion("pet")

      if (response) {
        println("Well done, the character has a pet!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "5") {
      val response: Boolean = board.askQuestion("beard")

      if (response) {
        println("Well done, the character has a beard!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "6") {
      val response: Boolean = board.askQuestion("hat")

      if (response) {
        println("Well done, the character has a hat!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "7") {
      val hairColour: String = readLine("Enter the hair colour!🔫: ").toLowerCase

      val response: Boolean = board.askQuestion("hair", hairColour)

      if (response) {
        println(s"Well done, the character has $hairColour hair!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "8") {
      val eyeColour: String = readLine("Enter the eye colour!🔫: ").toLowerCase

      val response: Boolean = board.askQuestion("eyes", eyeColour)

      if (response) {
        println(s"Well done, the character has $eyeColour eyes!")
      } else {
        println("Incorrect, try again!")
      }
    } else if (userQuestionChoice == "9") {
      val jumperColour: String = readLine("Enter the jumper colour!🔫: ").toLowerCase

      val response: Boolean = board.askQuestion("jumper", jumperColour)

      if (response) {
        println(s"Well done, the character has $jumperColour jumper!")
      } else {
        println("Incorrect, try again!")
      }
    } else {
      println("That's not a valid question choice.")
    }
  }

  /**
   * Prompts the user to choose a hint option and provides the corresponding hint.
   */
  private def giveHintOption(): Unit = {
    println("1. Tell me a fact about the character")
    println("2. Remove one wrong character from the game\n")

    val userHintChoice: String = readLine("Enter hint choice: ")

    if (userHintChoice == "1") {
      println(board.getRandomTraitHint)
    } else if (userHintChoice == "2") {
      println(s"A wrong character is: ${board.getRandomWrongCharacterName}")
    } else {
      println("That's not a valid hint choice.")
    }
  }

  /**
   * Starts the game and manages the game loop.
   */
    @tailrec
  private def startGame(board: Board): Unit = {
    println("Welcome to Guess Who!")
    println("Here are the characters on the board:\n")
    board.characters.foreach(character => println(character.name))
    println("\nTime to guess!")

    println(s"It's your turn ${board.getCurrentPlayerName}")
    println("Select one of the options below:\n")
    println("1. Guess character")
    println("2. Ask question")
    println("3. Give me a hint!\n")

    val userChoice: String = readLine("Your choice: ")
    println()

    userChoice match {
      case "1" =>
        guessNameOption()
        board.switchPlayer()
        startGame(board)
      // Recursive call with updated board state

      case "2" =>
        askQuestionOption()
        board.switchPlayer()
        startGame(board)
      // Recursive call with updated board state

      case "3" =>
        giveHintOption()
        board.switchPlayer()
        startGame(board)
      // Recursive call with updated board state

      case _ =>
        println("That's not a valid choice.")
        board.switchPlayer()
        startGame(board)
      // Recursive call with updated board state
    }
  }

  // Start the game with the initial board state
  startGame(board)

  //  private def startGame(): Unit = {
  //    println("Welcome to Quess Who!")
  //    println("Here are the characters on the board:\n")
  //    board.characters.foreach(character => println(character.name))
  //    println("\nTime to guess!")
  //
  //    while (true) {
  //      println(s"It's your turn ${board.getCurrentPlayerName}")
  //      println("Select one of the options below:\n")
  //      println("1. Guess character")
  //      println("2. Ask question")
  //      println("3. Give me a hint!\n")
  //
  //      val userChoice: String = readLine("Your choice: ")
  //      println()
  //
  //      if (userChoice == "1") {
  //        guessNameOption()
  //      } else if (userChoice == "2") {
  //        askQuestionOption()
  //      } else if (userChoice == "3") {
  //        giveHintOption()
  //      } else {
  //        println("That's not a valid choice.")
  //        board.switchPlayer()
  //      }
  //
  //      board.switchPlayer()
  //
  //      println()
  //    }
  //  }
  //
  //  // Start the game
  //  startGame()
}
