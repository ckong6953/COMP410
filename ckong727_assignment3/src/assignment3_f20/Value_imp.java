//-------------------------------------------------------------------
// put this Interface and Implementation into your code as is
// the grader may need these methods to examine the structure
// that your code creates
//-------------------------------------------------------------------
// you may add methods and structure as you need to
// for example, you may want a toString to help with your debugging
// but to not remove what we have given here
//-------------------------------------------------------------------

package assignment3_f20;

public class Value_imp implements Value {
  int idNum;
  double score;
  int age;

  public Value_imp(int n, double s, int a) {
    this.idNum=n; this.score=s; this.age=a;
  }
  
  @Override
  public void setIdNum (int n) { this.idNum = n; }
  @Override
  public int getIdNum() { return idNum; }
  @Override
  public void setScore (double s) { this.score = s; }
  @Override
  public double getScore() { return score; }
  @Override
  public void setAge (int n) { this.age = n; }
  @Override
  public int getAge() { return age; }
} 