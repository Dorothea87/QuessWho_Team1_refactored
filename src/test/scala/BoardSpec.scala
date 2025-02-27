import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.util.Random

class BoardSpec extends AnyWordSpec with Matchers {
  private val character = Character("Alice", Blue, Blonde, Red, Female, hasGlasses = true, hasBeard = false, hasHat = false, hasPet = true)

  private val characterList: List[Character] = List(character)

  private val player1: Player = Player("Andy")
  private val player2: Player = Player("April")

  private val boardTest = Board(characterList, player1, player2)

//  case class Character(name: String, eyeColour: Colour, hairColour: Colour, jumperColour: Colour, gender: Gender, hasGlasses: Boolean, hasBeard: Boolean, hasHat: Boolean, hasPet: Boolean)

  "guessCharacter" should {
    "return true" when {
      "the name matches the name of the character guessed" in {
        val input = boardTest.guessCharacter("Alice")
        val expectedResult = true
        input shouldBe expectedResult
      }
    }

    "return false" when {
      "the name doesn't match the name of the character guessed" in {
        val input = boardTest.guessCharacter("Tudor")
        val expectedResult = false
        input shouldBe expectedResult
      }
    }
  }

  "askQuestion" should {
    "return true" when {
      "userQuestion is female and the gender of character is female" in {
        val input = boardTest.askQuestion("female")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about hair colour and guess is correct" in {
        val input = boardTest.askQuestion("hair", "black")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is about eye colour and guess is correct" in {
        val input = boardTest.askQuestion("eyes", "blue")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about jumper colour and guess is correct" in {
        val input = boardTest.askQuestion("jumper", "red")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have glasses and they do" in {
        val input = boardTest.askQuestion("glasses")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have a pet and they do" in {
        val input = boardTest.askQuestion("pet")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have a beard and they do" in {
        val input = !boardTest.askQuestion("beard")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is whether they have a hat and they don't" in {
        val input = !boardTest.askQuestion("hat")
        val expectedResult = true
        input shouldBe expectedResult
      }
    }

    "return false" when {
      "userQuestion is male and the gender of character is female" in {
        val input = boardTest.askQuestion("male")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is about hair colour and guess is incorrect" in {
        val input = boardTest.askQuestion("hair", "blonde")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about eye colour and guess is incorrect" in {
        val input = boardTest.askQuestion("eyes", "green")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is about jumper colour and guess is incorrect" in {
        val input = boardTest.askQuestion("jumper", "blue")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have glasses and they don't" in {
        val input = !boardTest.askQuestion("glasses")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have a pet and they don't" in {
        val input = boardTest.askQuestion("pet")
        val expectedResult = true
        input shouldBe expectedResult
      }

      "userQuestion is about whether they have a beard and they don't" in {
        val input = boardTest.askQuestion("beard")
        val expectedResult = false
        input shouldBe expectedResult
      }

      "userQuestion is whether they have a hat and they do" in {
        val input = boardTest.askQuestion("hat")
        val expectedResult = false
        input shouldBe expectedResult
      }
    }
  }
  "getRandomCharacter" should {
    "return a random character from the character instances list" when {
      "the game is initiated" in {
        val selector = Board(characterList, player1, player2)
        val characters = characterList

        val randomCharacter = selector.getRandomCharacter

        characters should contain(randomCharacter)


      }
    }
  }
}
