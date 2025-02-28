/**
 * Represents a character in the game.
 *
 * @param name         The name of the character.
 * @param eyeColour    The eye color of the character.
 * @param hairColour   The hair color of the character.
 * @param jumperColour The jumper color of the character.
 * @param gender       Indicates if the character is male or female.
 * @param hasGlasses   Indicates if the character has glasses.
 * @param hasBeard     Indicates if the character has a beard.
 * @param hasHat       Indicates if the character has a hat.
 * @param hasPet       Indicates if the character has a pet.
 */
case class Character(name: String, eyeColour: Colour, hairColour: Colour, jumperColour: Colour, gender: Gender, hasGlasses: Boolean, hasBeard: Boolean, hasHat: Boolean, hasPet: Boolean)

sealed trait Gender

case object Male extends Gender

case object Female extends Gender


sealed trait Colour

case object Blue extends Colour

case object Blonde extends Colour

case object Red extends Colour

case object Brown extends Colour

case object Black extends Colour

case object Green extends Colour

case object Hazel extends Colour

case object Yellow extends Colour

case object Purple extends Colour

case object Orange extends Colour

case object Pink extends Colour