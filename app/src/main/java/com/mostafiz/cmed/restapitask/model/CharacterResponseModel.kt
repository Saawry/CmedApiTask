package com.mostafiz.cmed.restapitask.model
import com.beust.klaxon.*

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Ancestry::class,   { Ancestry.fromValue(it.string!!) },   { "\"${it.value}\"" })
    .convert(EyeColour::class,  { EyeColour.fromValue(it.string!!) },  { "\"${it.value}\"" })
    .convert(Gender::class,     { Gender.fromValue(it.string!!) },     { "\"${it.value}\"" })
    .convert(HairColour::class, { HairColour.fromValue(it.string!!) }, { "\"${it.value}\"" })
    .convert(House::class,      { House.fromValue(it.string!!) },      { "\"${it.value}\"" })
    .convert(Patronus::class,   { Patronus.fromValue(it.string!!) },   { "\"${it.value}\"" })
    .convert(Species::class,    { Species.fromValue(it.string!!) },    { "\"${it.value}\"" })
    .convert(Core::class,       { Core.fromValue(it.string!!) },       { "\"${it.value}\"" })
    .convert(Wood::class,       { Wood.fromValue(it.string!!) },       { "\"${it.value}\"" })

class CharacterResponseModel(elements: Collection<CharacterModel>) : ArrayList<CharacterModel>(elements) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = CharacterResponseModel(klaxon.parseArray<CharacterModel>(json)!!)
    }
}

data class CharacterModel (
    val id: String,
    val name: String,

    @Json(name = "alternate_names")
    val alternateNames: List<String>,

    val species: Species,
    val gender: Gender,
    val house: House,
    val dateOfBirth: String? = null,
    val yearOfBirth: Long? = null,
    val wizard: Boolean,
    val ancestry: Ancestry,
    val eyeColour: EyeColour,
    val hairColour: HairColour,
    val wand: Wand,
    val patronus: Patronus,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,

    @Json(name = "alternate_actors")
    val alternateActors: List<String>,

    val alive: Boolean,
    val image: String
)

enum class Ancestry(val value: String) {
    Empty(""),
    HalfBlood("half-blood"),
    HalfVeela("half-veela"),
    Muggle("muggle"),
    Muggleborn("muggleborn"),
    PureBlood("pure-blood"),
    QuarterVeela("quarter-veela"),
    Squib("squib");

    companion object {
        public fun fromValue(value: String): Ancestry = when (value) {
            ""              -> Empty
            "half-blood"    -> HalfBlood
            "half-veela"    -> HalfVeela
            "muggle"        -> Muggle
            "muggleborn"    -> Muggleborn
            "pure-blood"    -> PureBlood
            "quarter-veela" -> QuarterVeela
            "squib"         -> Squib
            else            -> throw IllegalArgumentException()
        }
    }
}

enum class EyeColour(val value: String) {
    Amber("amber"),
    Black("black"),
    Blue("blue"),
    Brown("brown"),
    Dark("dark"),
    Empty(""),
    Green("green"),
    Grey("grey"),
    Hazel("hazel"),
    Orange("orange"),
    PaleSilvery("pale, silvery"),
    Red("red"),
    White("white"),
    Yellow("yellow"),
    Yellowish("yellowish");

    companion object {
        public fun fromValue(value: String): EyeColour = when (value) {
            "amber"         -> Amber
            "black"         -> Black
            "blue"          -> Blue
            "brown"         -> Brown
            "dark"          -> Dark
            ""              -> Empty
            "green"         -> Green
            "grey"          -> Grey
            "hazel"         -> Hazel
            "orange"        -> Orange
            "pale, silvery" -> PaleSilvery
            "red"           -> Red
            "white"         -> White
            "yellow"        -> Yellow
            "yellowish"     -> Yellowish
            else            -> throw IllegalArgumentException()
        }
    }
}

enum class Gender(val value: String) {
    Female("female"),
    Male("male");

    companion object {
        public fun fromValue(value: String): Gender = when (value) {
            "female" -> Female
            "male"   -> Male
            else     -> throw IllegalArgumentException()
        }
    }
}

enum class HairColour(val value: String) {
    Bald("bald"),
    Black("black"),
    Blond("blond"),
    Blonde("blonde"),
    Brown("brown"),
    Dark("dark"),
    Dull("dull"),
    Empty(""),
    Ginger("ginger"),
    Grey("grey"),
    Red("red"),
    Sandy("sandy"),
    Silver("silver"),
    Tawny("tawny"),
    White("white");

    companion object {
        public fun fromValue(value: String): HairColour = when (value) {
            "bald"   -> Bald
            "black"  -> Black
            "blond"  -> Blond
            "blonde" -> Blonde
            "brown"  -> Brown
            "dark"   -> Dark
            "dull"   -> Dull
            ""       -> Empty
            "ginger" -> Ginger
            "grey"   -> Grey
            "red"    -> Red
            "sandy"  -> Sandy
            "silver" -> Silver
            "tawny"  -> Tawny
            "white"  -> White
            else     -> throw IllegalArgumentException()
        }
    }
}

enum class House(val value: String) {
    Empty(""),
    Gryffindor("Gryffindor"),
    Hufflepuff("Hufflepuff"),
    Ravenclaw("Ravenclaw"),
    Slytherin("Slytherin");

    companion object {
        public fun fromValue(value: String): House = when (value) {
            ""           -> Empty
            "Gryffindor" -> Gryffindor
            "Hufflepuff" -> Hufflepuff
            "Ravenclaw"  -> Ravenclaw
            "Slytherin"  -> Slytherin
            else         -> throw IllegalArgumentException()
        }
    }
}

enum class Patronus(val value: String) {
    Boar("boar"),
    Doe("doe"),
    Empty(""),
    Goat("goat"),
    Hare("hare"),
    Horse("horse"),
    JackRussellTerrier("Jack Russell terrier"),
    Lynx("lynx"),
    NonCorporeal("Non-Corporeal"),
    Otter("otter"),
    PersianCat("persian cat"),
    Stag("stag"),
    Swan("swan"),
    TabbyCat("tabby cat"),
    Weasel("weasel"),
    Wolf("wolf");

    companion object {
        public fun fromValue(value: String): Patronus = when (value) {
            "boar"                 -> Boar
            "doe"                  -> Doe
            ""                     -> Empty
            "goat"                 -> Goat
            "hare"                 -> Hare
            "horse"                -> Horse
            "Jack Russell terrier" -> JackRussellTerrier
            "lynx"                 -> Lynx
            "Non-Corporeal"        -> NonCorporeal
            "otter"                -> Otter
            "persian cat"          -> PersianCat
            "stag"                 -> Stag
            "swan"                 -> Swan
            "tabby cat"            -> TabbyCat
            "weasel"               -> Weasel
            "wolf"                 -> Wolf
            else                   -> throw IllegalArgumentException()
        }
    }
}

enum class Species(val value: String) {
    Acromantula("acromantula"),
    Cat("cat"),
    Centaur("centaur"),
    Dragon("dragon"),
    Ghost("ghost"),
    Giant("giant"),
    Goblin("goblin"),
    HalfGiant("half-giant"),
    HalfHuman("half-human"),
    Hippogriff("hippogriff"),
    HouseELF("house-elf"),
    Human("human"),
    Owl("owl"),
    Poltergeist("poltergeist"),
    ThreeHeadedDog("three-headed dog"),
    Vampire("vampire"),
    Werewolf("werewolf");

    companion object {
        public fun fromValue(value: String): Species = when (value) {
            "acromantula"      -> Acromantula
            "cat"              -> Cat
            "centaur"          -> Centaur
            "dragon"           -> Dragon
            "ghost"            -> Ghost
            "giant"            -> Giant
            "goblin"           -> Goblin
            "half-giant"       -> HalfGiant
            "half-human"       -> HalfHuman
            "hippogriff"       -> Hippogriff
            "house-elf"        -> HouseELF
            "human"            -> Human
            "owl"              -> Owl
            "poltergeist"      -> Poltergeist
            "three-headed dog" -> ThreeHeadedDog
            "vampire"          -> Vampire
            "werewolf"         -> Werewolf
            else               -> throw IllegalArgumentException()
        }
    }
}

data class Wand (
    val wood: Wood,
    val core: Core,
    val length: Double? = null
)

enum class Core(val value: String) {
    DragonHeartstring("dragon heartstring"),
    Empty(""),
    PhoenixFeather("phoenix feather"),
    UnicornHair("unicorn hair"),
    UnicornTailHair("unicorn tail-hair");

    companion object {
        public fun fromValue(value: String): Core = when (value) {
            "dragon heartstring" -> DragonHeartstring
            ""                   -> Empty
            "phoenix feather"    -> PhoenixFeather
            "unicorn hair"       -> UnicornHair
            "unicorn tail-hair"  -> UnicornTailHair
            else                 -> throw IllegalArgumentException()
        }
    }
}

enum class Wood(val value: String) {
    Ash("ash"),
    Birch("birch"),
    Cedar("cedar"),
    Cherry("cherry"),
    Chestnut("chestnut"),
    Cypress("cypress"),
    Elm("elm"),
    Empty(""),
    Fir("fir"),
    Hawthorn("hawthorn"),
    Holly("holly"),
    Hornbeam("hornbeam"),
    Larch("larch"),
    Mahogany("mahogany"),
    Oak("oak"),
    Vine("vine"),
    Walnut("walnut"),
    Willow("willow"),
    Yew("yew");

    companion object {
        public fun fromValue(value: String): Wood = when (value) {
            "ash"      -> Ash
            "birch"    -> Birch
            "cedar"    -> Cedar
            "cherry"   -> Cherry
            "chestnut" -> Chestnut
            "cypress"  -> Cypress
            "elm"      -> Elm
            ""         -> Empty
            "fir"      -> Fir
            "hawthorn" -> Hawthorn
            "holly"    -> Holly
            "hornbeam" -> Hornbeam
            "larch"    -> Larch
            "mahogany" -> Mahogany
            "oak"      -> Oak
            "vine"     -> Vine
            "walnut"   -> Walnut
            "willow"   -> Willow
            "yew"      -> Yew
            else       -> throw IllegalArgumentException()
        }
    }
}
