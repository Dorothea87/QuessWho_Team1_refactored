import scala.util.Random

/**
 * Represents a game board with characters and players.
 *
 * @param characters List of characters available in the game.
 * @param player1    The first player.
 * @param player2    The second player.
 */
case class Board(characters: List[Character], player1: Player, player2: Player) {
  /**
   * The current player.
   */
  private var currentPlayer: Player = _

  /**
   * Random number generator.
   */
  private val randomNumGen: Random = new Random()

  initialisePlayers()

  /**
   * Switches the current player to the opponent.
   */
  def switchPlayer(): Unit = currentPlayer = currentPlayer.opponent

  /**
   * Gets the name of the current player.
   *
   * @return The name of the current player.
   */
  def getCurrentPlayerName: String = currentPlayer.name

  /**
   * Gets a random character from the list of characters.
   *
   * @return A random character.
   */
  def getRandomCharacter: Character = {
    val randomCharacterIndex: Int = randomNumGen.nextInt(characters.length)
    println(randomCharacterIndex) //look into loggers in the future
    characters(randomCharacterIndex)
  }

  /**
   * Initializes the players by setting their opponents and assigning random characters to guess.
   */
  private def initialisePlayers(): Unit = {
    currentPlayer = player1
    player1.opponent = player2
    player2.opponent = player1
//    player1.characterToGuess = Character("Alice", Blue, Blonde, Red, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)
//    player2.characterToGuess = Character("Bob", Brown, Black, Blue, Male, hasGlasses = false, hasBeard = true, hasHat = true, hasPet = false)
    player1.characterToGuess = getRandomCharacter
    player2.characterToGuess = getRandomCharacter
  }

  /**
   * Gets a random wrong character name for the current player.
   *
   * @return A random wrong character name or a message if the hint has already been used.
   */
  def getRandomWrongCharacterName: String =
    if (!currentPlayer.hasUsedWrongCharacterHint) {
      currentPlayer.hasUsedWrongCharacterHint = true
      if (characters.length > 1) {
        val wrongCharacters: List[Character] = characters.filter(character => character != currentPlayer.characterToGuess)
        val randomWrongCharacterIndex: Int = randomNumGen.nextInt(wrongCharacters.length)
        wrongCharacters(randomWrongCharacterIndex).name
      } else "There's only ONE character to guess dummy😒! "
    } else "You already used this hint!"

  /**
   * Gets a random trait hint for the current player's character to guess.
   *
   * @return A random trait hint or a message if the hint has already been used.
   */
  def getRandomTraitHint: String = {
    if (!currentPlayer.hasUsedRandomTraitHint) {
      currentPlayer.hasUsedRandomTraitHint = true
      val characterTraits: List[String] = List(
        s"The character has ${currentPlayer.characterToGuess.eyeColour} eyes.",
        s"The character has ${currentPlayer.characterToGuess.hairColour} hair.",
        s"The character has a ${currentPlayer.characterToGuess.jumperColour} jumper.",
        s"The character is ${currentPlayer.characterToGuess.gender} male",
        s"The character ${if (currentPlayer.characterToGuess.hasPet) "has" else "does not have"} a pet.",
        s"The character ${if (currentPlayer.characterToGuess.hasHat) "has" else "does not have"} a hat.",
        s"The character ${if (currentPlayer.characterToGuess.hasBeard) "has" else "does not have"} a beard.",
        s"The character ${if (currentPlayer.characterToGuess.hasGlasses) "has" else "does not have"} glasses.",
      )
      val randomTraitIndex: Int = randomNumGen.nextInt(characterTraits.length)
      characterTraits(randomTraitIndex)
    } else "You already used this hint!"
  }

  /**
   * Checks if the given name matches the current player's character to guess.
   *
   * @param name The name to check.
   * @return True if the name matches, false otherwise.
   */
  def guessCharacter(name: String): Boolean = {
    currentPlayer.characterToGuess.name == name
  }

  /**
   * Asks a question about the current player's character to guess.
   *
   * @param userQuestion The question to ask.
   * @param guess        The guess for the question (optional).
   * @return True if the guess is correct, false otherwise.
   */
  def askQuestion(userQuestion: String, guess: String = ""): Boolean = {
    if (userQuestion == "male") {
      currentPlayer.characterToGuess.gender == Male
    } else if (userQuestion == "female") {
      currentPlayer.characterToGuess.gender == Female
    } else if (userQuestion == "hair") {
      currentPlayer.characterToGuess.hairColour.toString.toLowerCase == guess
    } else if (userQuestion == "eyes") {
      currentPlayer.characterToGuess.eyeColour.toString.toLowerCase == guess
    } else if (userQuestion == "jumper") {
      currentPlayer.characterToGuess.jumperColour.toString.toLowerCase == guess
    } else if (userQuestion == "glasses") {
      currentPlayer.characterToGuess.hasGlasses
    } else if (userQuestion == "pet") {
      currentPlayer.characterToGuess.hasPet
    } else if (userQuestion == "beard") {
      currentPlayer.characterToGuess.hasBeard
    } else if (userQuestion == "hat") {
      currentPlayer.characterToGuess.hasHat
    } else false
  }
}
