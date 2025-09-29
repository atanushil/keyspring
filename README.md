# Java Sample payload

```json
{
    "language": "java",
    "code": "import java.util.*;\n\npublic class Main {\n    public static boolean isPrime(int number) {\n        if (number <= 1) return false;\n        for (int i = 2; i <= Math.sqrt(number); i++) {\n            if (number % i == 0) return false;\n        }\n        return true;\n    }\n\n    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        System.out.print(\"Enter the limit up to which you want prime numbers: \");\n        int limit = sc.nextInt();\n\n        List<Integer> primes = new ArrayList<>();\n        for (int i = 2; i <= limit; i++) {\n            if (isPrime(i)) {\n                primes.add(i);\n            }\n        }\n\n        System.out.println(\"Prime numbers up to \" + limit + \": \" + primes);\n        sc.close();\n    }\n}",
    "input": "20"
}
