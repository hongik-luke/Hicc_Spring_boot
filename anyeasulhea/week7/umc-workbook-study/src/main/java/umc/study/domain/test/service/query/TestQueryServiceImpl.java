package umc.study.domain.test.service.query;

import lombok.RequiredArgsConstructor;
import umc.study.domain.test.exception.TestException;
import umc.study.domain.test.exception.code.TestErrorCode;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService {

    @Override
    public void checkFlag(Long flag) {
        if (flag == 1) {
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}