package pkg;

public class Main {

	public static void main(String[] args) {

//		pkg.Tree<Integer> tree = new pkg.RandomTreeGenerator().generate(20);
		//Tree from image in src directory
		Tree<Integer> tree = new Tree<>(0);
		Tree<Integer> b = new Tree<>(1);
		Tree<Integer> c = new Tree<>(2);
		Tree<Integer> d = new Tree<>(3);
		Tree<Integer> e = new Tree<>(4);
		Tree<Integer> f = new Tree<>(5);
		Tree<Integer> g = new Tree<>(6);
		Tree<Integer> h = new Tree<>(7);
		tree.setLeft(b);
		tree.setRight(c);
		b.setLeft(d);
		b.setRight(e);
		c.setLeft(f);
		c.setRight(g);
		f.setLeft(h);


		System.out.println(tree.dfs(6));

		System.out.println(tree.bfs(4, tree));

	}

}
