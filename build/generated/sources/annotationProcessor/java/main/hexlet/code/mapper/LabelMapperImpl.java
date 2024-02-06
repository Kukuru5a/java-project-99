package hexlet.code.mapper;

import hexlet.code.dto.label.LabelCreateDTO;
import hexlet.code.dto.label.LabelDTO;
import hexlet.code.dto.label.LabelUpdateDTO;
import hexlet.code.model.Label;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-06T22:43:53+0600",
    comments = "version: 1.6.0.Beta1, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class LabelMapperImpl extends LabelMapper {

    @Autowired
    private JsonNullableMapper jsonNullableMapper;

    @Override
    public Label map(LabelCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Label label = new Label();

        label.setName( dto.getName() );

        return label;
    }

    @Override
    public void map(LabelUpdateDTO dto, Label model) {
        if ( dto == null ) {
            return;
        }

        if ( jsonNullableMapper.isPresent( dto.getName() ) ) {
            model.setName( jsonNullableMapper.unwrap( dto.getName() ) );
        }
    }

    @Override
    public LabelDTO map(Label model) {
        if ( model == null ) {
            return null;
        }

        LabelDTO labelDTO = new LabelDTO();

        labelDTO.setId( model.getId() );
        labelDTO.setName( model.getName() );
        labelDTO.setCreatedAt( model.getCreatedAt() );

        return labelDTO;
    }

    @Override
    public List<LabelDTO> map(List<Label> models) {
        if ( models == null ) {
            return null;
        }

        List<LabelDTO> list = new ArrayList<LabelDTO>( models.size() );
        for ( Label label : models ) {
            list.add( map( label ) );
        }

        return list;
    }
}
