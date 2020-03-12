package reply.codechalange.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeUtility
{
    public int calculateWP(final Employee e1, final Employee e2) {

        final List<String> commonSkills = e1.getSkills().stream().filter(skill -> e2.getSkills().contains(skill)).collect(Collectors.toList());

        final Set<String> uncommonSkills = new HashSet<>(e1.getSkills());
        uncommonSkills.addAll(e2.getSkills());
        uncommonSkills.removeAll(commonSkills);


        return commonSkills.size() * uncommonSkills.size();
    }

    public int calculateBP(final Employee e1, final Employee e2) {

        if (e1.getCompany().equals(e2.getCompany())) {
            return e1.getBonus() * e2.getBonus();
        } else {
            return 0;
        }
    }

    public int calculateTP(final Employee e1, final Employee e2) {

        return calculateWP(e1, e2) + calculateBP(e1, e2);
    }


}
