package org.openjfx.bashMe;

import org.openjfx.bashMe.utils.ScriptUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static org.openjfx.bashMe.utils.ScriptPaths.TEST;


@ShellComponent
public class CommandRunner
{

    @ShellMethod(key = "test", value = "Test the script")
    public void test()
    {
        ScriptUtils.runner(TEST.getPath());
    }




}
