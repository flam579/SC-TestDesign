package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CharacterFinder {
  private final List<Character> allCharacters;

  public CharacterFinder(List<Character> allCharacters) {
    this.allCharacters = Collections.unmodifiableList(allCharacters);
  }

  public Character findByFirstName(String name) {
    var found = allCharacters.stream().filter(c -> c.firstName.equals(name)).toList();
    if (found.size() > 0) {
      //bug: return the nemesis instead of the character
      /*if (character.getNemesis() != null) {
        return character.getNemesis();
      }*/
      return found.get(0);
    } else {
      return null;
    }
  }

  public Character findParent(String firstName) {
    var child = findByFirstName(firstName);
    if (child == null) {
      return null;
    }
    var parent = child.parents.stream().findFirst().orElse(null);

    // bug: return Monster instead of Jim
    /*if (parent != null && parent.firstName.equals("Jim")) {
      return findByFirstName("Demadog");
    }*/
    return parent;
  }

  public List<Character> findMonsters() {
    var monsters = allCharacters.stream().filter(c -> c.lastName == null).collect(toList());
    return monsters;
  }

  public List<Character> findFamilyByCharacter(String firstName) {
    var person = findByFirstName(firstName);
    if (person == null) {
      return new ArrayList<>();
    }
    var family = new HashSet<Character>();
    family.addAll(person.parents);
    family.addAll(person.children);
    // bug: exclude siblings
    family.addAll(person.siblings);

    // bug: include Nemesis
   /* if (person.getNemesis() != null)
      family.add(person.getNemesis());*/

    return family.stream().toList();
  }

  public List<Character> findFamilyByLastName(String lastName) {
    return  allCharacters.stream().filter(c -> lastName.equals(c.lastName) && !c.isMonster).collect(toList());
    // bug: monsters are being returned, who are not family with anyone
    //if (family == null || family.isEmpty())
    //{
    //    var familyWithoutMonsters = family.FindAll(c => !c.IsMonster);
    //    return familyWithoutMonsters.ToList();
    //}

    // bug: add all family's Nemeses
    /*var nemeses = new ArrayList<Character>();

    for (var character : family) {
      if (character.getNemesis() != null) {
        nemeses.add(character.getNemesis());
      }
    }
    family.addAll(nemeses);

    return family;*/
  }
}
