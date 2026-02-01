# Countdown Letters Game

A Java implementation of the letters portion of the game show *Countdown*. Two players compete over 10 rounds to form the longest valid words from randomly generated letters.

**Course Project**: Object-Oriented Programming in Java (2023)

## Features

- Dictionary-based word validation using `words.txt`
- Random letter generation with player-specified vowel count (3-5)
- Duplicate letter validation
- Automatic best word finder
- 18-point bonus for 9-letter words

## Setup

```bash
# Compile
javac CountdownProject/*.java 

# Run (requires words.txt in same directory)
java CountdownProject.Countdown
```

## Gameplay

1. Choose number of vowels (3-5) each round
2. Program generates 9 random letters
3. Both players submit words
4. Longer valid word scores points equal to word length
5. After 10 rounds, highest score wins

## Rules

**Valid words must:**
- Appear in `words.txt`
- Use only available letters
- Respect letter frequency (duplicates count)

**Scoring:**
- Points = word length
- 9-letter word = 18 points
- Tie = both players score
- Invalid word = 0 points

## Requirements

- JDK 8+
- `words.txt` dictionary file

---

*Based on the British game show Countdown*
