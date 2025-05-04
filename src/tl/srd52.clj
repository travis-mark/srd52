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