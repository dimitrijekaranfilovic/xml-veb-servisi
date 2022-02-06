package rs.vakcinacija.zajednicko.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import rs.vakcinacija.zajednicko.model.RDFBoolean;
import rs.vakcinacija.zajednicko.model.RDFDate;
import rs.vakcinacija.zajednicko.model.RDFInteger;
import rs.vakcinacija.zajednicko.model.RDFString;

import java.util.Date;

public class ModelMapperFactory {
    static class RDFStringToString extends AbstractConverter<RDFString, String> {
        @Override
        protected String convert(RDFString rdfString) {
            return rdfString.getValue();
        }
    }

    static class StringToRDFString extends AbstractConverter<String, RDFString> {
        @Override
        protected RDFString convert(String s) {
            var rdf = new RDFString();
            rdf.setValue(s);
            return rdf;
        }
    }

    static class RDFBooleanToBoolean extends AbstractConverter<RDFBoolean, Boolean> {
        @Override
        protected Boolean convert(RDFBoolean rdfBoolean) {
            return rdfBoolean.getValue();
        }
    }

    static class BooleanToRDFBoolean extends AbstractConverter<Boolean, RDFBoolean> {
        @Override
        protected RDFBoolean convert(Boolean b) {
            var rdf = new RDFBoolean();
            rdf.setValue(b);
            return rdf;
        }
    }

    static class RDFIntegerToInteger extends AbstractConverter<RDFInteger, Integer> {
        @Override
        protected Integer convert(RDFInteger rdfInteger) {
            return rdfInteger.getValue();
        }
    }

    static class IntegerToRDFInteger extends AbstractConverter<Integer, RDFInteger> {
        @Override
        protected RDFInteger convert(Integer i) {
            var rdf = new RDFInteger();
            rdf.setValue(i);
            return rdf;
        }
    }

    static class RDFDateToDate extends AbstractConverter<RDFDate, Date> {
        @Override
        protected Date convert(RDFDate rdfDate) {
            return rdfDate.getValue();
        }
    }

    static class DateToRDFDate extends AbstractConverter<Date, RDFDate> {
        @Override
        protected RDFDate convert(Date d) {
            var rdf = new RDFDate();
            rdf.setValue(d);
            return rdf;
        }
    }

    public ModelMapper buildModelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(new RDFStringToString());
        modelMapper.addConverter(new StringToRDFString());
        modelMapper.addConverter(new RDFBooleanToBoolean());
        modelMapper.addConverter(new BooleanToRDFBoolean());
        modelMapper.addConverter(new RDFIntegerToInteger());
        modelMapper.addConverter(new IntegerToRDFInteger());
        modelMapper.addConverter(new RDFDateToDate());
        modelMapper.addConverter(new DateToRDFDate());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
