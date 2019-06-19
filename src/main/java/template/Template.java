package template;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

    private String templateText;
    private Map<String, String> variables;

    public Template(String templateText) {
        this.variables = new HashMap<String, String>();
        this.templateText = templateText;
    }

    public void set(String name, String value) {
        this.variables.put(name, value);
    }

    public String evaluate() {
        String result = templateText;

        result = replaceVariables(result);
        checkForMissingValues(result);

        return result;
    }

    private String replaceVariables(String result) {
        for (Entry<String, String> entry : variables.entrySet()) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }

    private void checkForMissingValues(String result) {
        Matcher m = Pattern.compile(".*\\$\\{.+\\}.*").matcher(result);
        if (m.find()) {
            throw new MissingValueException("No value for " + m.group());
        }
    }

    private void append(String segment, StringBuilder result) {
        


        if (segment.startsWith("${") && segment.endsWith("}")) {
           String var = segment.substring(2, segment.length() - 1);
            if (!variables.containsKey(var)) {
                throw new MissingValueException("No value for " + segment);
            }
            result.append(variables.get(var));
        } else {
            result.append(segment);
        }
 }
}
