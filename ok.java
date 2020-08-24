public class ok{
  int sum = 0;
  for(int i=1; i<1000; i++){
    i = (i*(i+1)/2)*(i*(i+1)/2);
    if(i/(i+5)==17) sum = sum + i;
  }
  print(sum);
}
