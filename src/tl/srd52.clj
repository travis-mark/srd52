(ns tl.srd52
  (:gen-class))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(def -ability-modifier-vector
  [-5 -4 -4 -3 -3 -2 -2 -1 -1 0 0 1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10])
(defn ability-modifier
  "SRD 5.2 pgs. 5-6
   
   Each ability has a modifier that you apply whenever you make a D20 Test with that ability. 
   An ability modifier is derived from its score, as shown in the Ability Modifiers table."
  [score]
  (if (<= 1 score 30)
    (-ability-modifier-vector (- score 1))
    (throw (IllegalArgumentException. (str "Score must be between 1 and 30, got: " score)))))

(def level-1-hit-points
  "SRD 5.2 pg. 22 - Level 1 Hit Points by Class"
  {:barbarian 12
   :fighter 10 :paladin 10 :ranger 10
   :bard 8 :cleric 8 :druid 8 :monk 8 :rogue 8 :warlock 8
   :sorcerer 6 :wizard 6})

(def hit-points-per-level
  "SRD 5.2 pg. 23 - Fixed Hit Points by Class"
  {:barbarian 7
   :fighter 6 :paladin 6 :ranger 6
   :bard 5 :cleric 5 :druid 5 :monk 5 :rogue 5 :warlock 5
   :sorcerer 4 :wizard 4})

(defn max-hit-points
  "Incomplete max health function.
   TODO: Add more cases."
  [character]
  (let [level (:level character)
        base-hit-points (level-1-hit-points (:class character))
        level-hit-points (hit-points-per-level (:class character))
        con-mod (ability-modifier (:constitution character))]
    (+ base-hit-points con-mod (* (- level 1) (+ level-hit-points con-mod)))))