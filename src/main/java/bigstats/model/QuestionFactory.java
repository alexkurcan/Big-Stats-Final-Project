package bigstats.model;

import java.util.List;

/**
 * Factory that populates a QuestionBank with real AP Statistics questions
 * organized by unit.
 *
 * Units included:
 *   1. Exploring One-Variable Data
 *   2. Exploring Two-Variable Data
 *   3. Collecting Data
 *   4. Probability & Random Variables
 *   5. Sampling Distributions
 *   6. Inference for Categorical Data
 *
 * Each unit has 10 questions (60 total).
 */
public class QuestionFactory {

    public static final String UNIT_1 = "Exploring One-Variable Data";
    public static final String UNIT_2 = "Exploring Two-Variable Data";
    public static final String UNIT_3 = "Collecting Data";
    public static final String UNIT_4 = "Probability & Random Variables";
    public static final String UNIT_5 = "Sampling Distributions";
    public static final String UNIT_6 = "Inference for Categorical Data";

    /** Returns a fully populated QuestionBank. */
    public static QuestionBank buildBank() {
        QuestionBank bank = new QuestionBank();
        addUnit1(bank);
        addUnit2(bank);
        addUnit3(bank);
        addUnit4(bank);
        addUnit5(bank);
        addUnit6(bank);
        return bank;
    }

    // ── Unit 1: Exploring One-Variable Data ──────────────────────────────────

    private static void addUnit1(QuestionBank bank) {
        bank.addQuestion(new Question(
            "Which measure of center is most resistant to outliers?",
            List.of("Mean", "Median", "Mode", "Range"),
            1, UNIT_1));

        bank.addQuestion(new Question(
            "A distribution is described as right-skewed. Which is typically true?",
            List.of("Mean < Median", "Mean > Median", "Mean = Median", "The IQR is negative"),
            1, UNIT_1));

        bank.addQuestion(new Question(
            "The IQR is defined as:",
            List.of("Q3 – Q1", "Max – Min", "Q2 – Q1", "Mean – Median"),
            0, UNIT_1));

        bank.addQuestion(new Question(
            "A data value is considered an outlier using the 1.5 × IQR rule if it is:",
            List.of("More than 2 SDs from the mean",
                    "Below Q1 − 1.5·IQR or above Q3 + 1.5·IQR",
                    "Below the minimum or above the maximum",
                    "More than 3 SDs from the mean"),
            1, UNIT_1));

        bank.addQuestion(new Question(
            "Which graphical display is best for showing the shape, center, and spread of a single quantitative variable?",
            List.of("Bar chart", "Pie chart", "Histogram", "Segmented bar chart"),
            2, UNIT_1));

        bank.addQuestion(new Question(
            "Standardizing a data set (z-scores) changes which of the following?",
            List.of("The shape of the distribution",
                    "The mean only",
                    "The mean and standard deviation",
                    "Neither the mean nor standard deviation"),
            2, UNIT_1));

        bank.addQuestion(new Question(
            "The standard deviation measures:",
            List.of("The range of the middle 50% of data",
                    "The average distance of data values from the mean",
                    "The difference between the max and min",
                    "The most frequently occurring value"),
            1, UNIT_1));

        bank.addQuestion(new Question(
            "In a Normal distribution, approximately what percent of data falls within 2 standard deviations of the mean?",
            List.of("68%", "90%", "95%", "99.7%"),
            2, UNIT_1));

        bank.addQuestion(new Question(
            "A z-score of −2.0 means the data value is:",
            List.of("2 standard deviations above the mean",
                    "2 standard deviations below the mean",
                    "2 IQRs below the median",
                    "2% below the mean"),
            1, UNIT_1));

        bank.addQuestion(new Question(
            "Which five-number summary component is the same as Q2?",
            List.of("Mean", "Mode", "Median", "IQR"),
            2, UNIT_1));
    }

    // ── Unit 2: Exploring Two-Variable Data ──────────────────────────────────

    private static void addUnit2(QuestionBank bank) {
        bank.addQuestion(new Question(
            "The correlation coefficient r measures:",
            List.of("The slope of the regression line",
                    "The strength and direction of a linear association",
                    "The percentage of variance explained",
                    "The causation between two variables"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "A correlation of r = −0.85 indicates:",
            List.of("A weak negative linear relationship",
                    "A strong positive linear relationship",
                    "A strong negative linear relationship",
                    "No linear relationship"),
            2, UNIT_2));

        bank.addQuestion(new Question(
            "The coefficient of determination r² represents:",
            List.of("The slope of the LSRL",
                    "The proportion of variation in y explained by x",
                    "The correlation between residuals and x",
                    "The intercept of the regression line"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "A residual is calculated as:",
            List.of("ŷ − y", "y − ŷ", "x − ŷ", "ŷ − x̄"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "Which of the following is NOT true about the Least Squares Regression Line?",
            List.of("It minimizes the sum of squared residuals",
                    "It always passes through (x̄, ȳ)",
                    "It guarantees causation between variables",
                    "Its slope can be computed from r, sx, and sy"),
            2, UNIT_2));

        bank.addQuestion(new Question(
            "Extrapolation in regression refers to:",
            List.of("Using the LSRL within the range of observed data",
                    "Predicting y for x values outside the data range",
                    "Removing outliers before fitting the line",
                    "Transforming data to achieve linearity"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "An influential point in regression is one that:",
            List.of("Has a large residual",
                    "Greatly changes the regression line when removed",
                    "Has a y-value far from the mean",
                    "Is always an outlier in the y direction"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "Correlation does NOT imply:",
            List.of("Association", "Causation", "Direction", "Strength"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "If the slope of the LSRL is 3.2, it means that for each 1-unit increase in x:",
            List.of("y decreases by 3.2 on average",
                    "y increases by 3.2 on average",
                    "r increases by 3.2",
                    "The residual increases by 3.2"),
            1, UNIT_2));

        bank.addQuestion(new Question(
            "A residual plot with no pattern suggests:",
            List.of("A nonlinear model is better",
                    "The linear model is appropriate",
                    "The correlation is near 1",
                    "Extrapolation is safe"),
            1, UNIT_2));
    }

    // ── Unit 3: Collecting Data ───────────────────────────────────────────────

    private static void addUnit3(QuestionBank bank) {
        bank.addQuestion(new Question(
            "Which sampling method gives every possible sample of size n an equal chance of being selected?",
            List.of("Stratified random sample",
                    "Cluster sample",
                    "Simple random sample",
                    "Systematic sample"),
            2, UNIT_3));

        bank.addQuestion(new Question(
            "Which of the following is an example of voluntary response bias?",
            List.of("A researcher surveys every 10th customer",
                    "An online poll where users choose to respond",
                    "A stratified sample based on age groups",
                    "A cluster sample of city blocks"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "In an experiment, a control group is used to:",
            List.of("Increase the placebo effect",
                    "Provide a baseline for comparison",
                    "Ensure double-blinding",
                    "Reduce the sample size"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "Random assignment in an experiment is important because it:",
            List.of("Increases sample size",
                    "Allows for cause-and-effect conclusions",
                    "Eliminates all confounding variables automatically",
                    "Ensures a representative sample"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "A study in which neither the subject nor the administrator knows which treatment is applied is called:",
            List.of("Single-blind", "Placebo-controlled", "Double-blind", "Randomized block"),
            2, UNIT_3));

        bank.addQuestion(new Question(
            "Confounding occurs when:",
            List.of("The sample is too large",
                    "Two variables' effects cannot be separated",
                    "The placebo effect is too strong",
                    "Random assignment is used"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "An observational study differs from an experiment because it:",
            List.of("Uses a larger sample",
                    "Does not impose a treatment on subjects",
                    "Always uses random sampling",
                    "Eliminates bias automatically"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "Blocking in an experiment is used to:",
            List.of("Increase the number of treatments",
                    "Control for a known source of variability",
                    "Eliminate the need for a control group",
                    "Ensure a voluntary response sample"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "Which is a potential source of non-sampling error?",
            List.of("Sample size too small",
                    "Response bias from question wording",
                    "Using stratified instead of simple random sampling",
                    "Taking a census instead of a sample"),
            1, UNIT_3));

        bank.addQuestion(new Question(
            "Results of an observational study can establish:",
            List.of("Causation", "Association", "Both causation and association", "Neither"),
            1, UNIT_3));
    }

    // ── Unit 4: Probability & Random Variables ────────────────────────────────

    private static void addUnit4(QuestionBank bank) {
        bank.addQuestion(new Question(
            "If P(A) = 0.4 and P(B) = 0.3 and A and B are independent, what is P(A and B)?",
            List.of("0.70", "0.12", "0.58", "0.10"),
            1, UNIT_4));

        bank.addQuestion(new Question(
            "Two events are mutually exclusive if:",
            List.of("P(A and B) = P(A)·P(B)",
                    "P(A or B) = P(A) + P(B)",
                    "P(A|B) = P(A)",
                    "P(A and B) = 1"),
            1, UNIT_4));

        bank.addQuestion(new Question(
            "The expected value E(X) of a discrete random variable is:",
            List.of("The most likely value of X",
                    "The median of X",
                    "Σ[x · P(X = x)]",
                    "The variance of X"),
            2, UNIT_4));

        bank.addQuestion(new Question(
            "If X is a Binomial random variable with n = 10 and p = 0.5, what is E(X)?",
            List.of("5", "2.5", "10", "0.5"),
            0, UNIT_4));

        bank.addQuestion(new Question(
            "The variance of a random variable X + c (where c is a constant) equals:",
            List.of("Var(X) + c", "Var(X) + c²", "Var(X)", "Var(X) · c"),
            2, UNIT_4));

        bank.addQuestion(new Question(
            "A geometric distribution models:",
            List.of("Number of successes in n trials",
                    "Number of trials until the first success",
                    "The probability of exactly k successes",
                    "The mean of a sample"),
            1, UNIT_4));

        bank.addQuestion(new Question(
            "Which condition is NOT required for a Binomial setting?",
            List.of("Fixed number of trials",
                    "Each trial is independent",
                    "At most two possible outcomes per trial",
                    "Probability of success changes each trial"),
            3, UNIT_4));

        bank.addQuestion(new Question(
            "P(A|B) represents:",
            List.of("P(A) multiplied by P(B)",
                    "The probability of A given that B has occurred",
                    "The joint probability of A and B",
                    "P(B) given that A has occurred"),
            1, UNIT_4));

        bank.addQuestion(new Question(
            "The standard deviation of a Binomial(n, p) distribution is:",
            List.of("np", "√(np)", "√(np(1−p))", "np(1−p)"),
            2, UNIT_4));

        bank.addQuestion(new Question(
            "If events A and B are independent, then:",
            List.of("P(A|B) = P(B)", "P(A|B) = P(A)", "P(A and B) = 0", "P(A or B) = 1"),
            1, UNIT_4));
    }

    // ── Unit 5: Sampling Distributions ───────────────────────────────────────

    private static void addUnit5(QuestionBank bank) {
        bank.addQuestion(new Question(
            "The Central Limit Theorem states that for large n, the sampling distribution of x̄ is:",
            List.of("Exactly Normal regardless of population shape",
                    "Approximately Normal regardless of population shape",
                    "Skewed in the same direction as the population",
                    "Uniform"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "The standard error of the sample mean (σ/√n) decreases as n:",
            List.of("Decreases", "Increases", "Stays the same", "Approaches 0"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "The mean of the sampling distribution of x̄ equals:",
            List.of("σ/√n", "μ/n", "μ", "x̄"),
            2, UNIT_5));

        bank.addQuestion(new Question(
            "For the sampling distribution of p̂ to be approximately Normal, which condition must hold?",
            List.of("n > 30",
                    "np ≥ 10 and n(1−p) ≥ 10",
                    "p = 0.5",
                    "The population is Normal"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "The standard deviation of the sampling distribution of p̂ is:",
            List.of("p(1−p)", "√(p(1−p)/n)", "p/n", "np(1−p)"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "A sampling distribution describes:",
            List.of("The distribution of individuals in the population",
                    "The distribution of a statistic over all possible samples",
                    "The distribution within a single sample",
                    "The margin of error for an estimate"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "Bias in a statistic means:",
            List.of("The statistic has high variability",
                    "The statistic consistently overestimates or underestimates the parameter",
                    "The standard error is too large",
                    "The sample size is too small"),
            1, UNIT_5));

        bank.addQuestion(new Question(
            "Which statement about increasing sample size is TRUE?",
            List.of("It eliminates bias",
                    "It increases variability",
                    "It decreases the standard error",
                    "It changes the shape of the population"),
            2, UNIT_5));

        bank.addQuestion(new Question(
            "The 10% condition for sampling distributions states:",
            List.of("At least 10 successes are required",
                    "n must be at least 10",
                    "The sample size must be less than 10% of the population",
                    "p must be close to 0.10"),
            2, UNIT_5));

        bank.addQuestion(new Question(
            "If σ = 12 and n = 36, the standard error of x̄ is:",
            List.of("12", "2", "6", "0.33"),
            1, UNIT_5));
    }

    // ── Unit 6: Inference for Categorical Data ────────────────────────────────

    private static void addUnit6(QuestionBank bank) {
        bank.addQuestion(new Question(
            "A 95% confidence interval means:",
            List.of("There is a 95% probability the parameter is in this specific interval",
                    "95% of sample statistics fall in this interval",
                    "If we repeat the procedure many times, ~95% of intervals will capture the parameter",
                    "The parameter equals the midpoint 95% of the time"),
            2, UNIT_6));

        bank.addQuestion(new Question(
            "Increasing the confidence level (e.g., from 90% to 99%) while keeping n constant will:",
            List.of("Decrease the margin of error",
                    "Increase the margin of error",
                    "Have no effect on the margin of error",
                    "Decrease the sample mean"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "In a one-proportion z-test, the null hypothesis typically states:",
            List.of("p ≠ p₀", "p > p₀", "p = p₀", "p < p₀"),
            2, UNIT_6));

        bank.addQuestion(new Question(
            "A Type I error occurs when you:",
            List.of("Fail to reject a false null hypothesis",
                    "Reject a true null hypothesis",
                    "Accept a false alternative hypothesis",
                    "Set α too small"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "A Type II error occurs when you:",
            List.of("Reject a true null hypothesis",
                    "Fail to reject a false null hypothesis",
                    "Set α too large",
                    "Use too large a sample"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "The p-value of a test is the probability of:",
            List.of("The null hypothesis being true",
                    "Making a Type II error",
                    "Obtaining results at least as extreme as observed, assuming H₀ is true",
                    "The alternative hypothesis being true"),
            2, UNIT_6));

        bank.addQuestion(new Question(
            "Which increases the power of a significance test?",
            List.of("Decreasing sample size",
                    "Increasing α",
                    "Moving H_a closer to H₀",
                    "Decreasing α"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "The margin of error in a confidence interval for a proportion is:",
            List.of("z* · p̂", "z* · √(p̂(1−p̂)/n)", "z* · n", "z* · p̂/n"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "A chi-square goodness-of-fit test is used when:",
            List.of("Comparing two means",
                    "Testing if a single categorical variable matches a claimed distribution",
                    "Testing association between two quantitative variables",
                    "Comparing proportions across two populations"),
            1, UNIT_6));

        bank.addQuestion(new Question(
            "The expected count in a chi-square test is calculated as:",
            List.of("(row total × column total) / grand total",
                    "observed − expected",
                    "observed / expected",
                    "grand total / number of cells"),
            0, UNIT_6));
    }
}
