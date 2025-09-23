from math import isqrt
from time import perf_counter
from typing import Iterable, List


def primes_by_trial(limit: int) -> List[int]:
    """
    Generate primes up to limit using optimized trial division.

    Optimizations:
      - Handle 2 separately, then test only odd candidates
      - Test divisors only up to sqrt(n)
      - Divisors step by 2 (skip even divisors)
    """
    if limit < 2:
        return []

    primes: List[int] = [2]
    for candidate in range(3, limit + 1, 2):
        root = isqrt(candidate)
        is_prime = True
        divisor = 3
        if candidate % 3 == 0 and candidate != 3:
            is_prime = False
        else:
            while divisor <= root and is_prime:
                if candidate % divisor == 0:
                    is_prime = False
                    break
                divisor += 2
        if is_prime:
            primes.append(candidate)
    return primes


def primes_by_sieve(limit: int) -> List[int]:
    """Generate primes up to limit using the Sieve of Eratosthenes."""
    if limit < 2:
        return []
    sieve = bytearray(b"\x01") * (limit + 1)
    sieve[0:2] = b"\x00\x00"  # 0 and 1 are not primes
    for p in range(2, isqrt(limit) + 1):
        if sieve[p]:
            step_start = p * p
            sieve[step_start : limit + 1 : p] = b"\x00" * (((limit - step_start) // p) + 1)
    return [i for i, flag in enumerate(sieve) if flag]


def print_per_line(items: Iterable[int], per_line: int = 5) -> None:
    count = 0
    for value in items:
        print(f"{value:>5}", end="")
        count += 1
        if count % per_line == 0:
            print()
    if count % per_line != 0:
        print()


def benchmark(limit: int) -> None:
    start = perf_counter()
    trial_primes = primes_by_trial(limit)
    t1 = perf_counter() - start

    start = perf_counter()
    sieve_primes = primes_by_sieve(limit)
    t2 = perf_counter() - start

    print(f"Trial division found {len(trial_primes)} primes in {t1:.4f}s")
    print(f"Sieve found          {len(sieve_primes)} primes in {t2:.4f}s")
    print("结论: 最耗时的是反复进行除法判断的素数判定循环。改进：改用埃拉托斯特尼筛法，\n"
          "它将昂贵的‘除法判断’替换为线性的标记操作，并且可进一步优化为只筛奇数/分块/位图。")


if __name__ == "__main__":
    LIMIT = 20000
    print("1~20000 的所有素数（每行 5 个）：")
    primes = primes_by_sieve(LIMIT)
    print_per_line(primes, per_line=5)

    print("\n简单性能分析：")
    benchmark(LIMIT)


