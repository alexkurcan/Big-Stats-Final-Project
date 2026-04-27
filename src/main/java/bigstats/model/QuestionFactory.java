package bigstats.model;

import java.util.List;

/**
 * Populates a QuestionBank with AP Statistics questions (10 per unit, 60 total).
 */
public class QuestionFactory {

    public static final String UNIT_1 = "Exploring One-Variable Data";
    public static final String UNIT_2 = "Exploring Two-Variable Data";
    public static final String UNIT_3 = "Collecting Data";
    public static final String UNIT_4 = "Probability & Random Variables";
    public static final String UNIT_5 = "Sampling Distributions";
    public static final String UNIT_6 = "Inference for Categorical Data";

    public static QuestionBank buildBank() {
        QuestionBank bank = new QuestionBank();

        // ── Unit 1 ────────────────────────────────────────────────────────────
        add(bank, UNIT_1, "Which measure of center is most resistant to outliers?",
            1, "Mean", "Median", "Mode", "Range");
        add(bank, UNIT_1, "A right-skewed distribution typically has:",
            1, "Mean < Median", "Mean > Median", "Mean = Median", "Negative IQR");
        add(bank, UNIT_1, "The IQR is defined as:",
            0, "Q3 – Q1", "Max – Min", "Q2 – Q1", "Mean – Median");
        add(bank, UNIT_1, "Using the 1.5×IQR rule, an outlier falls:",
            1, "More than 2 SDs from the mean",
               "Below Q1−1.5·IQR or above Q3+1.5·IQR",
               "Below the minimum or above the maximum",
               "More than 3 SDs from the mean");
        add(bank, UNIT_1, "Best display for shape, center, and spread of one quantitative variable?",
            2, "Bar chart", "Pie chart", "Histogram", "Segmented bar chart");
        add(bank, UNIT_1, "Standardizing (z-scores) changes which of the following?",
            2, "Shape only", "Mean only", "Mean and standard deviation", "Neither");
        add(bank, UNIT_1, "Standard deviation measures:",
            1, "Middle 50% range", "Average distance from the mean", "Max minus min", "Most frequent value");
        add(bank, UNIT_1, "In a Normal distribution, roughly what percent falls within 2 SDs of the mean?",
            2, "68%", "90%", "95%", "99.7%");
        add(bank, UNIT_1, "A z-score of −2.0 means the value is:",
            1, "2 SDs above the mean", "2 SDs below the mean", "2 IQRs below median", "2% below the mean");
        add(bank, UNIT_1, "Which five-number summary value equals Q2?",
            2, "Mean", "Mode", "Median", "IQR");

        // ── Unit 2 ────────────────────────────────────────────────────────────
        add(bank, UNIT_2, "The correlation coefficient r measures:",
            1, "Slope of LSRL", "Strength and direction of linear association", "% variance explained", "Causation");
        add(bank, UNIT_2, "r = −0.85 indicates:",
            2, "Weak negative", "Strong positive", "Strong negative", "No relationship");
        add(bank, UNIT_2, "The coefficient of determination r² represents:",
            1, "Slope of LSRL", "Proportion of variation in y explained by x", "Residual correlation", "Intercept");
        add(bank, UNIT_2, "A residual is calculated as:",
            1, "ŷ − y", "y − ŷ", "x − ŷ", "ŷ − x̄");
        add(bank, UNIT_2, "Which is NOT true about the LSRL?",
            2, "Minimizes squared residuals", "Passes through (x̄, ȳ)", "Guarantees causation", "Slope from r·sy/sx");
        add(bank, UNIT_2, "Extrapolation means:",
            1, "Using LSRL within data range", "Predicting y outside data range", "Removing outliers", "Transforming data");
        add(bank, UNIT_2, "An influential point is one that:",
            1, "Has a large residual", "Greatly changes the line when removed", "Has y far from mean", "Is always an outlier");
        add(bank, UNIT_2, "Correlation does NOT imply:",
            1, "Association", "Causation", "Direction", "Strength");
        add(bank, UNIT_2, "A slope of 3.2 means for each 1-unit increase in x:",
            1, "y decreases by 3.2", "y increases by 3.2", "r increases by 3.2", "Residual increases by 3.2");
        add(bank, UNIT_2, "A residual plot with no pattern suggests:",
            1, "Nonlinear model is better", "Linear model is appropriate", "r is near 1", "Extrapolation is safe");

        // ── Unit 3 ────────────────────────────────────────────────────────────
        add(bank, UNIT_3, "Which method gives every size-n sample an equal chance?",
            2, "Stratified", "Cluster", "Simple random", "Systematic");
        add(bank, UNIT_3, "Example of voluntary response bias:",
            1, "Every 10th customer surveyed", "Online poll where users choose to respond",
               "Stratified sample by age", "Cluster sample of city blocks");
        add(bank, UNIT_3, "A control group is used to:",
            1, "Increase placebo effect", "Provide a baseline for comparison", "Ensure double-blinding", "Reduce sample size");
        add(bank, UNIT_3, "Random assignment in an experiment allows:",
            1, "Larger sample size", "Cause-and-effect conclusions", "Elimination of all confounders", "Representative sample");
        add(bank, UNIT_3, "Neither subjects nor administrators know the treatment — this is:",
            2, "Single-blind", "Placebo-controlled", "Double-blind", "Randomized block");
        add(bank, UNIT_3, "Confounding occurs when:",
            1, "Sample is too large", "Two variables' effects can't be separated",
               "Placebo effect is strong", "Random assignment is used");
        add(bank, UNIT_3, "An observational study does NOT:",
            1, "Use a large sample", "Impose a treatment on subjects", "Use random sampling", "Eliminate bias");
        add(bank, UNIT_3, "Blocking controls for:",
            1, "Number of treatments", "A known source of variability", "The need for a control group", "Voluntary response");
        add(bank, UNIT_3, "A source of non-sampling error:",
            1, "Sample size too small", "Response bias from question wording",
               "Using stratified sampling", "Taking a census");
        add(bank, UNIT_3, "An observational study can establish:",
            1, "Causation", "Association", "Both", "Neither");

        // ── Unit 4 ────────────────────────────────────────────────────────────
        add(bank, UNIT_4, "P(A)=0.4, P(B)=0.3, independent. P(A and B) = ?",
            1, "0.70", "0.12", "0.58", "0.10");
        add(bank, UNIT_4, "Two events are mutually exclusive if:",
            1, "P(A and B)=P(A)·P(B)", "P(A or B)=P(A)+P(B)", "P(A|B)=P(A)", "P(A and B)=1");
        add(bank, UNIT_4, "Expected value E(X) of a discrete random variable is:",
            2, "Most likely value", "Median", "Σ[x·P(X=x)]", "Variance");
        add(bank, UNIT_4, "Binomial(n=10, p=0.5). E(X) = ?",
            0, "5", "2.5", "10", "0.5");
        add(bank, UNIT_4, "Var(X + c) where c is a constant equals:",
            2, "Var(X)+c", "Var(X)+c²", "Var(X)", "Var(X)·c");
        add(bank, UNIT_4, "Geometric distribution models:",
            1, "Successes in n trials", "Trials until first success", "Probability of exactly k successes", "Sample mean");
        add(bank, UNIT_4, "Which condition is NOT required for a Binomial setting?",
            3, "Fixed n", "Independent trials", "Two outcomes per trial", "Changing probability of success");
        add(bank, UNIT_4, "P(A|B) represents:",
            1, "P(A)·P(B)", "Probability of A given B occurred", "Joint probability", "P(B) given A");
        add(bank, UNIT_4, "SD of Binomial(n,p) is:",
            2, "np", "√(np)", "√(np(1−p))", "np(1−p)");
        add(bank, UNIT_4, "If A and B are independent, then:",
            1, "P(A|B)=P(B)", "P(A|B)=P(A)", "P(A and B)=0", "P(A or B)=1");

        // ── Unit 5 ────────────────────────────────────────────────────────────
        add(bank, UNIT_5, "The Central Limit Theorem says the sampling dist. of x̄ is:",
            1, "Exactly Normal always", "Approximately Normal for large n", "Skewed like population", "Uniform");
        add(bank, UNIT_5, "Standard error σ/√n decreases as n:",
            1, "Decreases", "Increases", "Stays the same", "Approaches 0");
        add(bank, UNIT_5, "Mean of the sampling distribution of x̄ equals:",
            2, "σ/√n", "μ/n", "μ", "x̄");
        add(bank, UNIT_5, "For p̂ to be approx. Normal, which must hold?",
            1, "n > 30", "np ≥ 10 and n(1−p) ≥ 10", "p = 0.5", "Population is Normal");
        add(bank, UNIT_5, "SD of the sampling distribution of p̂ is:",
            1, "p(1−p)", "√(p(1−p)/n)", "p/n", "np(1−p)");
        add(bank, UNIT_5, "A sampling distribution describes:",
            1, "Distribution of individuals", "Distribution of a statistic over all samples",
               "Distribution within one sample", "Margin of error");
        add(bank, UNIT_5, "Bias in a statistic means:",
            1, "High variability", "Consistently over/underestimates the parameter", "SE is large", "Sample too small");
        add(bank, UNIT_5, "Increasing sample size:",
            2, "Eliminates bias", "Increases variability", "Decreases standard error", "Changes population shape");
        add(bank, UNIT_5, "The 10% condition says:",
            2, "At least 10 successes needed", "n must be ≥ 10",
               "n must be < 10% of population", "p must be near 0.10");
        add(bank, UNIT_5, "σ=12, n=36. Standard error of x̄ = ?",
            1, "12", "2", "6", "0.33");

        // ── Unit 6 ────────────────────────────────────────────────────────────
        add(bank, UNIT_6, "A 95% confidence interval means:",
            2, "95% probability parameter is in this interval",
               "95% of statistics fall here",
               "~95% of repeated intervals capture the parameter",
               "Parameter equals midpoint 95% of the time");
        add(bank, UNIT_6, "Raising confidence level (90%→99%), same n, the margin of error:",
            1, "Decreases", "Increases", "Stays the same", "Depends on p");
        add(bank, UNIT_6, "In a one-proportion z-test, H₀ typically states:",
            2, "p ≠ p₀", "p > p₀", "p = p₀", "p < p₀");
        add(bank, UNIT_6, "A Type I error occurs when you:",
            1, "Fail to reject a false H₀", "Reject a true H₀", "Accept false Hₐ", "Set α too small");
        add(bank, UNIT_6, "A Type II error occurs when you:",
            1, "Reject a true H₀", "Fail to reject a false H₀", "Set α too large", "Use too large a sample");
        add(bank, UNIT_6, "The p-value is the probability of:",
            2, "H₀ being true", "Type II error",
               "Results at least as extreme as observed, assuming H₀", "Hₐ being true");
        add(bank, UNIT_6, "Which increases the power of a test?",
            1, "Decreasing n", "Increasing α", "Moving Hₐ closer to H₀", "Decreasing α");
        add(bank, UNIT_6, "Margin of error for a proportion CI is:",
            1, "z*·p̂", "z*·√(p̂(1−p̂)/n)", "z*·n", "z*·p̂/n");
        add(bank, UNIT_6, "A chi-square goodness-of-fit test is used when:",
            1, "Comparing two means",
               "Testing if one categorical variable matches a claimed distribution",
               "Testing association between two quantitative variables",
               "Comparing proportions across two populations");
        add(bank, UNIT_6, "Expected count in a chi-square test is:",
            0, "(row total × col total) / grand total", "observed − expected",
               "observed / expected", "grand total / cells");

        return bank;
    }

    /** Convenience helper: build and add a question with up to 4 choices. */
    private static void add(QuestionBank bank, String unit, String prompt,
                            int correct, String a, String b, String c, String d) {
        bank.addQuestion(new Question(prompt, List.of(a, b, c, d), correct, unit));
    }
}
