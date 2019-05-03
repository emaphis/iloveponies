(ns training-day)

(use 'clojure.repl)

;; Exercise 3 
;; Write a Clojure expression in the REPL that, using get, gets the first
;; character in the string "abracadabra".
;; This exercise does not give any points and you do not need to return it. 

(get "abracadabra" 0)
;; => \a


;;; Functions
;;;;;;;;;;;;;;

;; function expression
(fn [who] (str "hello, " who "!"))

;; applying a function expression
((fn [who] (str "hello, " who "!")) "Edward")
;; => "hello, Edward!"


;; Exercise 4 
;; Call the following function in the REPL with your name.
(fn [name] (str "Welcome to Rivendell mr " name))

((fn [name] (str "Welcome to Rivendell mr " name)) "Edward")
;; => "Welcome to Rivendell mr Edward"


;; naming a function
(def hello (fn [who] (str "hello, " who "!")))

(hello "Edward")
;; => "hello, Edward!"

(defn hello
  "Gives out personalized greeting."
  [who]
  (str "Hello, " who "!"))


;; Exercise 5 
;; Give a name answer to the answer to life the universe and everything.
;; This is the first exercise in which you need to modify the
;; file src/training_day.clj.
;; Remember to run the tests with lein midje.

(def answer 42)



(def hai "O HAI!")


;; Exercise 6 
;; Write the function (square x) that takes a number as a parameter and
;; multiplies it with itself.

(defn square [x]
  (* x x))

(square 2)
;; => 4
(square 3)
;; => 9


(defn average [a b]
  (/ (+ a b) 2))

(average 2 4)
;; => 3
(average 1 2)
;; => 3/2
