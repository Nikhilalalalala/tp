package com.eva.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.eva.model.EvaDatabase;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.comment.Comment;
import com.eva.model.person.Address;
import com.eva.model.person.Email;
import com.eva.model.person.Name;
import com.eva.model.person.Person;
import com.eva.model.person.Phone;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.applicant.ApplicationStatus;
import com.eva.model.person.applicant.InterviewDate;
import com.eva.model.person.staff.Staff;
import com.eva.model.person.staff.leave.Leave;
import com.eva.model.tag.Tag;

/**
 * Contains utility methods for populating {@code EvaDatabase} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("marketing"), getCommentSet("title 1|2010-10-10|works well")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("business"), getCommentSet("title 2|2010-11-10|hi", "title 3|2010-12-10|hi")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("tech"), getCommentSet("title 4|2010-09-10|hi", "title 5|2010-08-10|hi")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("tech"), getCommentSet("title 6|2010-10-10|hi")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("business"), getCommentSet("title 7|2010-07-10|hi")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("tech"), getCommentSet("title 8|2011-11-10|hi"))
        };
    }

    public static Staff[] getSampleStaffs() {
        // TODO: Add some meaningful leave samples
        Set<Leave> leaves = new HashSet<>();
        return Arrays.stream(getSamplePersons())
                .map(person -> new Staff(person, leaves))
                .toArray(Staff[]::new);
    }

    public static Applicant[] getSampleApplicants() {
        return new Applicant[] {
            new Applicant(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("late"),
                    getCommentSet("Interests|2010-10-10|passionate about marketing"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getTagSet("tech"),
                    getCommentSet("Duration|2010-11-10|Internship from Jan to Jun"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("summer"), getCommentSet(), new ApplicationStatus("received")),
            new Applicant(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("NUS"),
                    getCommentSet("Review|2010-10-10|Have experience in marketing at big firm before"),
                    Optional.of(new InterviewDate("02/11/2020")), new ApplicationStatus("processing")),
            new Applicant(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("business"), getCommentSet("title 7|2010-07-10|hi"),
                    new ApplicationStatus("received")),
            new Applicant(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("tech"), getCommentSet("title 8|2011-11-10|hi"),
                    new ApplicationStatus("received"))
        };
    }

    public static ReadOnlyEvaDatabase<Person> getSamplePersonDatabase() {
        EvaDatabase<Person> sampleAb = new EvaDatabase<>();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEvaDatabase<Staff> getSampleStaffDatabase() {
        EvaDatabase<Staff> sampleAb = new EvaDatabase<>();
        for (Staff samplePerson : getSampleStaffs()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyEvaDatabase<Applicant> getSampleApplicantDatabase() {
        EvaDatabase<Applicant> sampleAb = new EvaDatabase<>();
        for (Applicant samplePerson : getSampleApplicants()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a comment set containing the list of strings given.
     */
    public static Set<Comment> getCommentSet(String... strings) {
        List<String> list = Arrays.asList(strings);
        return list.stream()
                .map(string -> new Comment(LocalDate.parse(string.split("\\|", 3)[1]),
                        string.split("\\|", 3)[2], string.split("\\|", 3)[0]))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a leave set containing the list of strings given.
     */
    public static Set<Leave> getLeaveSet(String... strings) {
        return Arrays.stream(strings)
                .map(Leave::new)
                .collect(Collectors.toSet());
    }

}
