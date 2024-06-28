package RentCar;

import java.sql.SQLException;
import java.util.List;

import RentCarModel.CarManagementDTO;

public interface RentCarRepository {

	// 수정
	void updateRentCar(int id, int carcum) throws SQLException;

	// 추가
	void addRentCar(CarManagementDTO dto) throws SQLException;

	// 삭제
	void DeleteRentCar(int carid,String name,int carnum) throws SQLException;

	// 조회
	List<CarManagementDTO> viewRentCar() throws SQLException;

	// 부분조회?
	List<CarManagementDTO> selectViewRentCar() throws SQLException;

	// 정렬?
	List<CarManagementDTO> orderRentCar() throws SQLException;
}
