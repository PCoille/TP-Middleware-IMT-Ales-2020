package net.coille.middleware.client.ui.cli;

public class CLIStandardOutput implements CLIOutput {
    @Override
    public void println(String output) {
        System.out.println(output);
    }

    @Override
    public void println() {
        System.out.println();
    }
}
