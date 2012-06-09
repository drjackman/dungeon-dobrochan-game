
// ********************************
// *** Список всех юнитов ***
// ********************************
// Прежде чем добавить описание юнита надо добавить его в этот список.
// Название в кавычках является идентификатором типа
// и должно совпадать с названием соответствующей переменной(после var).
var Units =
[
  "SkeletonWarrior",
  "BlackDragon"
];

// *******************************************
// *** Описание конкретных юнитов ***
// *******************************************
var SkeletonWarrior = {
  name: "Скелет мечник",
  image: "SkeletonWarrior60",
  imageBig: "SkeletonWarrior250",
  description: "Сухие, старые кости шевелятся, когда в глазницах давно погибшего война загорается желтый огонь. Может показаться, что со светом в пещерах лучше. Может и так. Во всяком случае видно, от кого бежать.",

  bLvl: 2,
  bStr: 8,
  bStam: 7,
  bAgi: 7,
  bSens: 7,
  bWill: 1,

  skMelee: 0.9,
  skRange: 0.9,
  skDodge: 0.8,
  skTactic: 0.7,
  skMagic: 0.5,
  skContr: 0.5,
  skConcent: 0.5,

  weaponMelee: MELEE_HANDS,
  movement: MOVEMENT_GROUND,

  bSize: 2,
  bRace: RACE_UNDEAD
}

var BlackDragon = {
  name: "Черный дракон",
  image: "BlackDragon90",
  imageBig: "BlackDragon250",
  description: "Огромная рептилия, прекрасная и устрашающая, черным росчерком пересекающая небо, вызывает восторг и вселяет ужас; она не достижима с земли. На земле драконов можно увидеть лишь изредка - во время отдыха, или у зияющих провалов пещер, где они обитают. Люди часто рассказывают одну и ту же историю: якобы сотня крестьян, вооруженная лишь вилами и топорами, смогла одолеть черного дракона. Стоит ли говорить, что все это - глупые россказни.",

  bLvl: 6,
  bStr: 12,
  bStam: 12,
  bAgi: 10,
  bSens: 8,
  bWill: 8,

  skMelee: 1.1,
  skRange: 0.5,
  skDodge: 1.0,
  skTactic: 1.0,
  skMagic: 0.8,
  skContr: 0.8,
  skConcent: 0.8,

  weaponMelee: MELEE_CLAWS,
  weaponRange: RANGE_NONE,
  movement: MOVEMENT_FLY,

  bSize: 3,
  bRace: RACE_DRAGON,
  traits: [ "Trait_Spikes" ]
}
