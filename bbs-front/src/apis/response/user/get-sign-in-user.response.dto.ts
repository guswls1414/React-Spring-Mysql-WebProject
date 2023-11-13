import { User } from 'types/interface';
import ResponseDto from '../response.dto';

export default interface GetSignInUserResonseDto extends ResponseDto, User {
    
}