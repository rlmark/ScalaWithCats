package cats.show

import java.util.{Date, UUID}

import printable.Cat

object ShowWithCats {
  import cats.Show
  import cats.instances.int._ // Each import provides instances of all Cats’ type classes for a specific parameter type
  import cats.instances.string._

  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  val intAsString: String = showInt.show(3295)
  val stringAsString: String = showString.show("skfdlsac")
}

object ShowWithCatsExtensions {
  import cats.Show
  import cats.instances.int._ // Each import provides instances of all Cats’ type classes for a specific parameter type
  import cats.instances.string._
  import cats.syntax.show._ // This is the extension method import for nicer syntax

  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  "Hey hey".show
  28377892.show

  implicit val dateShow: Show[Date] = { // Can define custom instances of Show typeclass like so
    new Show[Date] {
      override def show(t: Date): String = s"${t.toInstant().toString} time stuff"
    }
  }
  // Or like this
  implicit val uuidShow: Show[UUID] = {
    Show.show( (uuid: UUID) => s"$uuid is a UUID")
  }

  implicit val catShower: Show[Cat] = {
    Show.show( (cat: Cat) => s"${cat.name} is a ${cat.age} year old ${cat.color} cat")
  }

  new Date().show
  UUID.randomUUID().show
}