import 'package:flutter/material.dart';
import 'dart:math';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: MathGamePage(),
    );
  }
}

class MathGamePage extends StatefulWidget {
  @override
  State<MathGamePage> createState() => _MathGamePageState();
}

class _MathGamePageState extends State<MathGamePage> {
  int num1 = 0;
  int num2 = 0;
  String operator = '+';
  int correctAnswer = 0;
  int wrongAnswer = 0;
  String message = '';

  @override
  void initState() {
    super.initState();
    generateQuestion();
  }

  void generateQuestion() {
    final random = Random();
    final operators = ['+', '-', '×', '÷'];
    operator = operators[random.nextInt(operators.length)];
    
    if (operator == '+') {
      num1 = random.nextInt(20) + 1;
      num2 = random.nextInt(20) + 1;
      correctAnswer = num1 + num2;
    } else if (operator == '-') {
      num1 = random.nextInt(20) + 10;
      num2 = random.nextInt(num1) + 1;
      correctAnswer = num1 - num2;
    } else if (operator == '×') {
      num1 = random.nextInt(10) + 1;
      num2 = random.nextInt(10) + 1;
      correctAnswer = num1 * num2;
    } else if (operator == '÷') {
      num2 = random.nextInt(10) + 1;
      correctAnswer = random.nextInt(10) + 1;
      num1 = num2 * correctAnswer;
    }
    
    wrongAnswer = correctAnswer + random.nextInt(10) + 1;
    message = '';
  }

  void checkAnswer(int answer) {
    if (answer == correctAnswer) {
      setState(() {
        message = 'Tebrikler!';
      });
    }
    Future.delayed(const Duration(seconds: 1), () {
      setState(() {
        generateQuestion();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Hızlı Matematik'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              '$num1 $operator $num2 = ?',
              style: const TextStyle(fontSize: 40),
            ),
            const SizedBox(height: 40),
            if (message.isNotEmpty)
              Text(
                message,
                style: const TextStyle(fontSize: 24, color: Colors.green),
              ),
            const SizedBox(height: 40),
            ElevatedButton(
              onPressed: () => checkAnswer(correctAnswer),
              child: Text('$correctAnswer'),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => checkAnswer(wrongAnswer),
              child: Text('$wrongAnswer'),
            ),
          ],
        ),
      ),
    );
  }
}
