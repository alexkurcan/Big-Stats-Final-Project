package bigstats.model;

import java.util.List;

public class QuestionFactory {
    public static final String UNIT_1 = "Exploring One-Variable Data";
    public static final String UNIT_2 = "Exploring Two-Variable Data";
    public static final String UNIT_3 = "Collecting Data";
    public static final String UNIT_4 = "Probability & Random Variables";
    public static final String UNIT_5 = "Sampling Distributions";
    public static final String UNIT_6 = "Inference for Categorical Data";

    public static QuestionBank buildBank() {
        QuestionBank b = new QuestionBank();

        // Unit 1
        q(b,UNIT_1,"Most resistant measure of center?",1,"Mean","Median","Mode","Range");
        q(b,UNIT_1,"Right-skewed distribution typically has:",1,"Mean<Median","Mean>Median","Mean=Median","Negative IQR");
        q(b,UNIT_1,"IQR is defined as:",0,"Q3–Q1","Max–Min","Q2–Q1","Mean–Median");
        q(b,UNIT_1,"1.5×IQR outlier rule: value is outlier if:",1,"More than 2 SDs from mean","Below Q1−1.5·IQR or above Q3+1.5·IQR","Outside min/max","More than 3 SDs from mean");
        q(b,UNIT_1,"Best display for shape/center/spread of one variable?",2,"Bar chart","Pie chart","Histogram","Segmented bar chart");
        q(b,UNIT_1,"Standardizing (z-scores) changes:",2,"Shape only","Mean only","Mean and standard deviation","Nothing");
        q(b,UNIT_1,"Standard deviation measures:",1,"Middle 50% range","Average distance from the mean","Max minus min","Most frequent value");
        q(b,UNIT_1,"Within 2 SDs of mean in a Normal distribution:",2,"68%","90%","95%","99.7%");
        q(b,UNIT_1,"A z-score of −2.0 means the value is:",1,"2 SDs above mean","2 SDs below mean","2 IQRs below median","2% below mean");
        q(b,UNIT_1,"Five-number summary value equal to Q2:",2,"Mean","Mode","Median","IQR");

        // Unit 2
        q(b,UNIT_2,"Correlation r measures:",1,"Slope of LSRL","Strength/direction of linear association","% variance explained","Causation");
        q(b,UNIT_2,"r = −0.85 indicates:",2,"Weak negative","Strong positive","Strong negative","No relationship");
        q(b,UNIT_2,"r² represents:",1,"Slope of LSRL","Proportion of variation in y explained by x","Residual correlation","Intercept");
        q(b,UNIT_2,"A residual equals:",1,"ŷ−y","y−ŷ","x−ŷ","ŷ−x̄");
        q(b,UNIT_2,"Which is NOT true about the LSRL?",2,"Minimizes squared residuals","Passes through (x̄,ȳ)","Guarantees causation","Slope from r·sy/sx");
        q(b,UNIT_2,"Extrapolation means predicting y:",1,"Within data range","Outside data range","After removing outliers","After transforming data");
        q(b,UNIT_2,"An influential point greatly changes:",1,"Residuals","The regression line when removed","y mean","Correlation");
        q(b,UNIT_2,"Correlation does NOT imply:",1,"Association","Causation","Direction","Strength");
        q(b,UNIT_2,"Slope of 3.2 means per 1-unit increase in x:",1,"y decreases 3.2","y increases 3.2","r increases 3.2","Residual increases 3.2");
        q(b,UNIT_2,"Residual plot with no pattern suggests:",1,"Nonlinear model better","Linear model is appropriate","r near 1","Extrapolation is safe");

        // Unit 3
        q(b,UNIT_3,"Every size-n sample has equal chance — this is:",2,"Stratified","Cluster","Simple random","Systematic");
        q(b,UNIT_3,"Example of voluntary response bias:",1,"Every 10th customer","Online poll users choose to join","Stratified by age","Cluster of city blocks");
        q(b,UNIT_3,"Control group provides:",1,"Placebo effect","Baseline for comparison","Double-blinding","Smaller sample");
        q(b,UNIT_3,"Random assignment allows:",1,"Larger n","Cause-and-effect conclusions","Eliminating all confounders","Representative sample");
        q(b,UNIT_3,"Neither subjects nor administrators know treatment — this is:",2,"Single-blind","Placebo-controlled","Double-blind","Randomized block");
        q(b,UNIT_3,"Confounding occurs when:",1,"Sample too large","Two variables' effects can't be separated","Placebo too strong","Random assignment used");
        q(b,UNIT_3,"Observational study does NOT:",1,"Use large sample","Impose a treatment","Use random sampling","Eliminate bias");
        q(b,UNIT_3,"Blocking controls for:",1,"Number of treatments","A known source of variability","Need for control group","Voluntary response");
        q(b,UNIT_3,"Source of non-sampling error:",1,"Sample too small","Response bias from question wording","Using stratified sampling","Taking a census");
        q(b,UNIT_3,"Observational study can establish:",1,"Causation","Association","Both","Neither");

        // Unit 4
        q(b,UNIT_4,"P(A)=0.4, P(B)=0.3, independent. P(A∩B)=?",1,"0.70","0.12","0.58","0.10");
        q(b,UNIT_4,"Mutually exclusive events: P(A∪B) =",1,"P(A)·P(B)","P(A)+P(B)","P(A|B)","1");
        q(b,UNIT_4,"E(X) of a discrete random variable is:",2,"Most likely value","Median","Σ[x·P(X=x)]","Variance");
        q(b,UNIT_4,"Binomial(n=10, p=0.5). E(X)=?",0,"5","2.5","10","0.5");
        q(b,UNIT_4,"Var(X+c) where c is a constant equals:",2,"Var(X)+c","Var(X)+c²","Var(X)","Var(X)·c");
        q(b,UNIT_4,"Geometric distribution models:",1,"Successes in n trials","Trials until first success","Probability of k successes","Sample mean");
        q(b,UNIT_4,"NOT required for a Binomial setting:",3,"Fixed n","Independent trials","Two outcomes per trial","Changing probability");
        q(b,UNIT_4,"P(A|B) means:",1,"P(A)·P(B)","P(A) given B occurred","Joint probability","P(B) given A");
        q(b,UNIT_4,"SD of Binomial(n,p) is:",2,"np","√(np)","√(np(1−p))","np(1−p)");
        q(b,UNIT_4,"If A and B are independent:",1,"P(A|B)=P(B)","P(A|B)=P(A)","P(A∩B)=0","P(A∪B)=1");

        // Unit 5
        q(b,UNIT_5,"CLT says sampling dist. of x̄ for large n is:",1,"Exactly Normal","Approximately Normal","Skewed like population","Uniform");
        q(b,UNIT_5,"σ/√n decreases as n:",1,"Decreases","Increases","Stays same","Approaches 0");
        q(b,UNIT_5,"Mean of sampling distribution of x̄ equals:",2,"σ/√n","μ/n","μ","x̄");
        q(b,UNIT_5,"For p̂ to be approx. Normal, we need:",1,"n>30","np≥10 and n(1−p)≥10","p=0.5","Normal population");
        q(b,UNIT_5,"SD of sampling distribution of p̂ is:",1,"p(1−p)","√(p(1−p)/n)","p/n","np(1−p)");
        q(b,UNIT_5,"A sampling distribution describes:",1,"Population individuals","A statistic over all possible samples","One sample's distribution","Margin of error");
        q(b,UNIT_5,"Bias means the statistic:",1,"Has high variability","Consistently over/underestimates","Has large SE","Has small n");
        q(b,UNIT_5,"Increasing sample size:",2,"Eliminates bias","Increases variability","Decreases standard error","Changes population shape");
        q(b,UNIT_5,"The 10% condition: n must be:",2,"At least 10","≥10 for successes","<10% of population","Near p=0.10");
        q(b,UNIT_5,"σ=12, n=36. SE of x̄=?",1,"12","2","6","0.33");

        // Unit 6
        q(b,UNIT_6,"95% confidence interval means:",2,"95% chance parameter is here","95% of statistics fall here","~95% of repeated intervals capture parameter","Parameter equals midpoint 95% of time");
        q(b,UNIT_6,"Raising confidence level (same n), margin of error:",1,"Decreases","Increases","Stays same","Depends on p");
        q(b,UNIT_6,"H₀ in one-proportion z-test typically states:",2,"p≠p₀","p>p₀","p=p₀","p<p₀");
        q(b,UNIT_6,"Type I error: you",1,"Fail to reject false H₀","Reject true H₀","Accept false Hₐ","Set α too small");
        q(b,UNIT_6,"Type II error: you",1,"Reject true H₀","Fail to reject false H₀","Set α too large","Use too large a sample");
        q(b,UNIT_6,"p-value is probability of:",2,"H₀ being true","Type II error","Results at least as extreme assuming H₀","Hₐ being true");
        q(b,UNIT_6,"Which increases test power?",1,"Decrease n","Increase α","Move Hₐ closer to H₀","Decrease α");
        q(b,UNIT_6,"Margin of error for proportion CI:",1,"z*·p̂","z*·√(p̂(1−p̂)/n)","z*·n","z*·p̂/n");
        q(b,UNIT_6,"Chi-square goodness-of-fit tests if:",1,"Two means differ","One categorical variable matches claimed distribution","Two quantitative variables are associated","Two proportions differ");
        q(b,UNIT_6,"Expected count in chi-square test:",0,"(row×col)/grand total","observed−expected","observed/expected","grand total/cells");

        return b;
    }

    private static void q(QuestionBank bank, String unit, String prompt,
                          int correct, String a, String b, String c, String d) {
        bank.addQuestion(new Question(prompt, List.of(a, b, c, d), correct, unit));
    }
}
