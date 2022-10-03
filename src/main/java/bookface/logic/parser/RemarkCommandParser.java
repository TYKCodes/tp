package bookface.logic.parser;

import static java.util.Objects.requireNonNull;
import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CliSyntax.PREFIX_REMARK;

import bookface.commons.core.index.Index;
import bookface.commons.exceptions.IllegalValueException;
import bookface.logic.commands.RemarkCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.person.Remark;

public class RemarkCommandParser {
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, new Remark(remark));
    }
}
