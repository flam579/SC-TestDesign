package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharactersTest {
  public List<Character> allTestData() {
    var joyce = new Character("Joyce", "Byers");
    var jim = new Character("Jim", "Hopper");
    var mike = new Character("Mike", "Wheeler");
    var eleven = new Character("Eleven");
    var dustin = new Character("Dustin", "Henderson");
    var lucas = new Character("Lucas", "Sinclair");
    var nancy = new Character("Nancy", "Wheeler");
    var jonathan = new Character("Jonathan", "Byers");
    var will = new Character("Will", "Byers");
    var karen = new Character("Karen", "Wheeler");
    var steve = new Character("Steve", "Harrington");
    var mindflayer = new Character("Mindflayer", true);
    var demagorgon = new Character("Demagorgon", true);
    var demadog = new Character("Demadog", true);

    joyce.addChild(jonathan);
    joyce.addChild(will);
    jim.addChild(eleven);
    karen.addChild(nancy);
    karen.addChild(mike);

    eleven.setNemesis(demagorgon);
    will.setNemesis(mindflayer);
    dustin.setNemesis(demadog);

    return Arrays.asList(
            joyce,
            jim,
            mike,
            will,
            eleven,
            dustin,
            lucas,
            nancy,
            jonathan,
            mindflayer,
            demagorgon,
            demadog,
            karen,
            steve);
  }

  @Test
  public void findCharacterByFirstName() {
    var finder = new CharacterFinder(allTestData());
    var character = finder.findByFirstName("Jim");
    assertEquals("Jim", character.firstName,"first name not matching Character");
    assertNull(finder.findByFirstName("firstName"));
  }

  @Test
  public void findCharacterByFirstName_when_CharactershasNemesis() {
    var finder = new CharacterFinder(allTestData());
    var character = finder.findByFirstName("Eleven");
    assertEquals("Eleven", character.firstName,"returns the nemesis instead of the character");
  }

  @Test
  public void findCharacterParents_when_CharactershasNemesis(){
    var finder = new CharacterFinder(allTestData());
    var character = finder.findParent("Eleven");
    assertEquals("Jim", character.firstName,"returns the nemesis instead of the parent");
  }

  @Test
  public void findFamilyByCharacter_when_siblings(){
    var finder = new CharacterFinder(allTestData());
    var charactersList = finder.findFamilyByCharacter("Mike");
    assertEquals(2, charactersList.size(),"doesn't return siblings");
    assertTrue(charactersList.contains(new Character("Karen", "Wheeler")));
    assertTrue(charactersList.contains(new Character("Nancy", "Wheeler")),"doesn't return siblings");
  }

  @Test
  public void findFamilyByCharacter_when_CharactershasNemesis(){
    var finder = new CharacterFinder(allTestData());
    var charactersList = finder.findFamilyByCharacter("Eleven");
    assertEquals(Arrays.asList(
            new Character("Jim", "Hopper")),charactersList,"returns the nemesis instead of the character");
  }

  @Test
  public void findFamilyByLastName_When_lastName_na(){
    var finder = new CharacterFinder(allTestData());
    var charactersList = finder.findFamilyByLastName("na");
    assertTrue(charactersList.isEmpty(), "monsters are being returned, who are not family with anyone");
  }

  @Test
  public void findFamilyByLastName_When_MembersHasNemesis(){
    var finder = new CharacterFinder(allTestData());
    var charactersList = finder.findFamilyByLastName("Byers");
    assertEquals(3, charactersList.size());
    assertTrue(charactersList.contains(new Character("Will", "Byers")));
    assertTrue(charactersList.contains(new Character("Joyce", "Byers")));
    assertTrue(charactersList.contains(new Character("Jonathan", "Byers")));
    assertFalse(charactersList.contains(new Character("Mindflayer", true)));
  }
}